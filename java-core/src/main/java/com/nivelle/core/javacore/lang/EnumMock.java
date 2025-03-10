package com.nivelle.core.javacore.lang;

import com.nivelle.core.pojo.MyEnum;

/**
 * Enum
 *
 * @author nivelle
 * @date 2019/06/16
 */
public class EnumMock {


    public static void main(String[] args) {

        /**
         *  Enum 反编译：
         *
         *  public final class com.nivelle.base.pojo.MyEnum extends java.lang.Enum<com.nivelle.base.pojo.MyEnum> {
         *   public static final com.nivelle.base.pojo.MyEnum ONE;
         *   public static final com.nivelle.base.pojo.MyEnum TWO;
         *   public static final com.nivelle.base.pojo.MyEnum THIRD;
         *   public static com.nivelle.base.pojo.MyEnum[] values();
         *   public static com.nivelle.base.pojo.MyEnum valueOf(java.lang.String);
         *   public int getType();
         *   public java.lang.String getDesc();
         *   static {};
         * }
         *
         */


        /**
         * 1. 枚举类型实际上会被编译成一个对应的类，这个类继承了 Enum 类，Enum 有 name 和 ordinal 两个实例变量，在构造方法中需要传递,name(),toString(),ordinal().compareTo(), equlas()方法都是由Enum类根据其实例变量name和ordinal实现的。
         *
         * 2. values()和valueOf()是编译器给每个枚举类型自动添加的方法
         *
         * 3. 枚举有一个私有的构造方法，接受name(String)和ordinal(int)，传递给父类，私有表示不能在外部创新新的实例
         *
         * 4. 枚举值实际是个静态变量,也是final的，不能被修改。
         *
         * 5. 一般枚举变量会转换为对应的类变量，在switch语句中，枚举值会被转换为对应的 ordinal 值。可以看出，枚举类型本质上也是类，但由于编译器做了很多事情，因此它的使用更为简单和安全。
         *
         */

        MyEnum oneEnum = MyEnum.ONE;

        MyEnum twoEnum = MyEnum.TWO;

        MyEnum threenEnum = MyEnum.THIRD;


        System.out.println("枚举类名称:" + oneEnum.getClass());

        System.out.println("枚举类名称:" + oneEnum.getDeclaringClass());

        System.out.println(oneEnum.getType());

        /**
         * 通过类型获得指定的枚举，valueOf（)静态方法，返回对应的枚举值
         */
        System.out.println("获取指定枚举:" + MyEnum.valueOf(MyEnum.class, "TWO").getDesc());

        System.out.println("获取指定枚举:" + MyEnum.valueOf("ONE").getDesc());


        /**
         * 获取指定枚举的名称，和toString()方法返回的字面值一样
         */
        System.out.println("指定枚举的名称:" + oneEnum.name());
        /**
         * 使用 == 是否是同一个对象和使用equals比较一样的
         */
        System.out.println("枚举比较:" + oneEnum.equals(twoEnum));

        /**
         * 遍历枚举集合,获得枚举数组
         */
        System.out.print("遍历枚举集合:" + MyEnum.values()[0].getType());
        System.out.println();
        System.out.println("遍历枚举集合:" + MyEnum.values()[1].getType());

        /**
         * 表示枚举值在声明时的顺序，从0开始
         */
        System.out.println("ordinal is:" + oneEnum.ordinal());

        /**
         * 枚举都实现了comparable接口，可以使用compareTo接口比较ordinal的大小
         */
        System.out.println(oneEnum.compareTo(oneEnum));


    }
}
