package com.nivelle.core.javacore.patterns.proxy;

import com.nivelle.core.pojo.User;

import java.util.Objects;

public class ProxyObject implements CompanyUser {

    private RealObject realObject;
    @Override
    public boolean doHardWork(User user) {

        System.out.println("代理对象做艰难的工作");

        return true;

    }
    @Override
    public boolean doEasyWork(User user) {

        if (Objects.isNull(realObject)) {
            realObject = new RealObject();
            realObject.doEasyWork(user);
        } else {
            realObject.doEasyWork(user);
        }


        return true;
    }


}
