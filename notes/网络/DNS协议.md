### DNS协议
#### 我们之前已经了解过ARP协议。
如果说ARP协议是用来将IP地址转换为MAC地址，那么DNS协议则是用来将域名转换为IP地址（也可以将IP地址转换为相应的域名地址）。
我们都知道，TCP/IP中使用的是IP地址和端口号来确定网络上某一台主机上的某一个程序，不免有人有疑问，为什么不用域名来直接进行通信呢？

- 1. 因为IP地址是固定长度的，IPv4是32位，IPv6是128位，而域名是变长的，不便于计算机处理。
- 2. IP地址对于用户来说不方便记忆，但域名便于用户使用，例如www.baidu.com这是百度的域名。
   总结一点就是IP地址是面向主机的，而域名则是面向用户的。
   hosts文件
   域名和IP的对应关系保存在一个叫hosts文件中。
   最初，通过互联网信息中心来管理这个文件，如果有一个新的计算机想接入网络，或者某个计算IP变更都需要到信息中心申请变更hosts文件。其他计算机也需要定期更新，才能上网。
   但是这样太麻烦了，就出现了DNS系统。

### DNS系统
一个组织的系统管理机构, 维护系统内的每个主机的IP和主机名的对应关系
如果新计算机接入网络，将这个信息注册到数据库中
用户输入域名的时候，会自动查询DNS服务器，由DNS服务器检索数据库，得到对应的IP地址
我们可以通过命令查看自己的hosts文件：

在域名解析的过程中仍然会优先查找hosts文件的内容。
DNS理论知识
### 一、DNS域名结构
1、域名的层次结构
域名系统必须要保持唯一性。
为了达到唯一性的目的，因特网在命名的时候采用了层次结构的命名方法：
1. 每一个域名（本文只讨论英文域名）都是一个标号序列（labels），用字母（A-Z，a-z，大小写等价）、数字（0-9）和连接符（-）组成
2. 标号序列总长度不能超过255个字符，它由点号分割成一个个的标号（label）
3. 每个标号应该在63个字符之内，每个标号都可以看成一个层次的域名。
4. 级别最低的域名写在左边，级别最高的域名写在右边。
   域名服务主要是基于UDP实现的，服务器的端口号为53。
   关于域名的层次结构，如下图所示：
![](https://img-blog.csdn.net/20180529182740527?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhaWR1XzM3OTY0MDcx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
eg :我们熟悉的，www.baidu.com
1. com: 一级域名. 表示这是一个企业域名。同级的还有 “net”(网络提供商), “org”(⾮非盈利组织) 等。
2. baidu: 二级域名，指公司名。
3. www: 只是一种习惯用法。

2、域名的分级
域名可以划分为各个子域，子域还可以继续划分为子域的子域，这样就形成了顶级域、二级域、三级域等。
![](https://img-blog.csdn.net/20180529183822818?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhaWR1XzM3OTY0MDcx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

其中顶级域名分为：国家顶级域名、通用顶级域名、反向域名。

国家顶级域名	中国:cn， 美国:us，英国uk…
通用顶级域名	com公司企业，edu教育机构，gov政府部门，int国际组织，mil军事部门 ，net网络，org非盈利组织…
反向域名	arpa，用于PTR查询（IP地址转换为域名）
### 二、域名服务器
域名是分层结构，域名服务器也是对应的层级结构。
有了域名结构，还需要有一个东西去解析域名，域名需要由遍及全世界的域名服务器去解析，域名服务器实际上就是装有域名系统的主机。

由高向低进行层次划分，可分为以下几大类：

分类	作用
根域名服务器	最高层次的域名服务器，本地域名服务器解析不了的域名就会向其求助
顶级域名服务器	负责管理在该顶级域名服务器下注册的二级域名
权限域名服务器	负责一个区的域名解析工作
本地域名服务器	当一个主机发出DNS查询请求时，这个查询请求首先发给本地域名服务器
注：一个域名服务器所负责的范围，或者说有管理权限的范围，就称为区
我们需要注意的是：
1. 每个层的域名上都有自己的域名服务器，最顶层的是根域名服务器
2. 每一级域名服务器都知道下级域名服务器的IP地址
3. 为了容灾, 每一级至少设置两个或以上的域名服务器

### 三、域名解析过程
域名解析总体可分为一下过程：
(1) 输入域名后, 先查找自己主机对应的域名服务器，域名服务器先查找自己的数据库中的数据.
(2) 如果没有， 就向上级域名服务器进行查找， 依次类推
(3) 最多回溯到根域名服务器, 肯定能找到这个域名的IP地址
(4) 域名服务器自身也会进行一些缓存， 把曾经访问过的域名和对应的IP地址缓存起来, 可以加速查找过程
具体可描述如下：
1. 主机先向本地域名服务器进行递归查询
2. 本地域名服务器采用迭代查询，向一个根域名服务器进行查询
3. 根域名服务器告诉本地域名服务器，下一次应该查询的顶级域名服务器的IP地址
4. 本地域名服务器向顶级域名服务器进行查询
5. 顶级域名服务器告诉本地域名服务器，下一步查询权限服务器的IP地址
6. 本地域名服务器向权限服务器进行查询
7. 权限服务器告诉本地域名服务器所查询的主机的IP地址
8. 本地域名服务器最后把查询结果告诉主机
   
![](https://img-blog.csdn.net/20180529191603529?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2JhaWR1XzM3OTY0MDcx/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

上文我们提出了两个概念：递归查询和迭代查询
（1）递归查询：本机向本地域名服务器发出一次查询请求，就静待最终的结果。如果本地域名服务器无法解析，自己会以DNS客户机的身份向其它域名服务器查询，直到得到最终的IP地址告诉本机
（2）迭代查询：本地域名服务器向根域名服务器查询，根域名服务器告诉它下一步到哪里去查询，然后它再去查，每次它都是以客户机的身份去各个服务器查询。

通俗地说，递归就是把一件事情交给别人，如果事情没有办完，哪怕已经办了很多，都不要把结果告诉我，我要的是你的最终结果，而不是中间结果；如果你没办完，请你找别人办完。
迭代则是我交给你一件事，你能办多少就告诉我你办了多少，然后剩下的事情就由我来办。
————————————————
版权声明：本文为CSDN博主「honeyRJ」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/baidu_37964071/article/details/80500825