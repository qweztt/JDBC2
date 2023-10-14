### JDBC知识梳理

> 1.JDBC的基础知识：
>> 基本步骤：注册驱动 - - > 获取数据库连接对象 - - > 获取执行SQL语句的对象 - - - > 执行SQL - - ->
> 处理结果 - - > 关闭连接的对象
>

```java
Class.forName("com.mysql.cj.jdbc.Driver");
//在MySQL5.0以上版本已经不需要手动注册驱动

        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/数据库名",username,password);
//如果sql都是默认配置那么可以简写成："jdbc:mysql:///数据库名"

        Statement stmt=conn.createStatement();
//执行SQL的对象，常用于 Data Manipulation Language（DML）//增/删/改  insert/delete/update

        String sql="insert into 表名 values (,)";
        String sql1="select * from account";

        stmt.executeUpdate(sql);//更新语句
//返回值为一个整形数据，代表被影响的行数

        ResultSet rs=stmt.executeQuery(sql1);//查询语句
//结果集对象，封装查询结果 - - - > ResultSet

        while(rs.next()){
        System.out.println(rs.getString("blance"));
        }


        conn.close();
        stmt.close();
/*为什么需要关闭连接？
 * 答：这个连接是与数据库服务器的一个连接，虽然你的方法结束了，但是这个资源依然存在数据库连接并没有释放
 * 
 * 为什么在JDBC对数据库访问结束后，要按先关闭ResultSet，然后关闭PreparedStatement，最后关闭Connection，直接关闭Connection不就行了吗？
 * 
 * 答1：感觉上好象是只要把connection给关闭了，系统就能正常运行了。 那在查询或是其它操作中，如果只关闭Connection，不作ResultSet 和 Statement 的关闭的话，对系统性能是否会有影响呢。或者是其它实方面的不良影响。
如果你不使用连接池，那么就没有什么问题，一旦Connection关闭，数据库物理连接就被释放，所有相关Java资源也可以被GC回收了。 但是如果你使用连接池，那么请注意，Connection关闭并不是物理关闭，只是归还连接池，
* 所以PreparedStatement和ResultSet都被持有，并且实际占用相关的数据库的游标资源，在这种情况下，只要长期运行，往往就会报“游标超出数据库允许的最大值”的错误，导致程序无法正 常访问数据库

 * 答2：因为你打开的时候有顺序，
打开时：Connection -> Statement -> ResultSet
关闭时：ResultSet-> Statement -> Connection
 * */
```

#### Statement对象会有SQL注入问题

> sql 注入, 简单的讲就是, 用户的输入导致 sql 语句的含义改变, 从而导致一些不符合预期的情况出现.
>> String sql = "select * from user where name = '" + un + "' and password = '" + pw + "'";
>>> 我们还原一下 sql 语句, 拼接后的 sql 语句为：          
> > > select * from user where name = 'xxx' and password = 'XXX' or '1' = '1'     
> 在MySQL中执行时会把数据库中所有的数据都查询出来

#### 问题解决------ PreparedStatement对象

PreparedStatement ps = conn.prepareStatement(String sql);

```java
String sql="select * from user where name = ? and password = ?";
// 下面一行执行完, DBMS会将框架先编译好
        PreparedStatement ps=conn.prepareStatement(sql);
// 下面给?传值, 第1个?下标为1, 第2个?下标为2, JDBC 中的下标都是从1开始的
        // setSting(index, str) 为,为第index个?获取字符串str填入,会在传入的字符串两边加上单引号
        // 所以不用?两边不用加单引号
        // setInt(index, int) 为获取int型的数据
        ps.setString(1,un);
        ps.setString(2,pw);
        // 执行 sql 语句, 下面的函数就不用传入 sql 语句了, 因为此时的数据库操作对象中已经有了sql的信息
        rs=ps.executeQuery();
```

### 总结：绝大多数情况下都是使用PreparedStatement

## Properties类：

> 需要一个配置文件*.properties，“*”代表名字，可任取。  
> 主要的作用是通过修改配置文件可以方便的修改代码中的参数，实现不用改class文件即可灵活变更参数。

```
url=jdbc:mysql://127.0.0.1:3306/test
user_name=root
password=root
//将来可以直接在此处修改配置信息，而不用更改源码
```

```java
//读取资源文件，获取值
//动态获取src路径下文件的方式    jdbc.properties的路径
ClassLoader classLoader=类名.class.getClassLoader();

        URL resource=classLoader.getResource("文件名.properties");

        String path=resource.getPath();

//2.加载文件
        pro.load(new FileReader(path));

        url=pro.getProperty("url");
        user=pro.getProperty("user");
        password=pro.getProperty("password");
        driver=pro.getProperty("driver");
```

### 数据库连接池 - - - > [数据库连接池](https://blog.csdn.net/CrankZ/article/details/82874158)

### JdbcTemplate基本使用：

>JdbcTemplate是Spring对JDBC的封装，目的是使JDBC更加易于使用。JdbcTemplate是Spring的一部分。JdbcTemplate处理了资源的建立和释放。他帮助我们避免一些常见的错误，比如忘了总要关闭连接。他运行核心的JDBC工作流，
> 如Statement的建立和执行，而我们<font color=red>只需要提供SQL语句和提取结果</font>

在JdbcTemplate中执行SQL语句的方法大致分为3类：
> execute：可以执行所有SQL语句，一般用于执行DDL语句。   
> update：用于执行INSERT、UPDATE、DELETE等DML语句。  
> queryXxx：用于DQL数据查询语句。
