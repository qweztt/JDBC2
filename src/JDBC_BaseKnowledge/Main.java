package JDBC_BaseKnowledge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
    public static void main(String[] args) throws Exception {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
//        2.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///Data","root","root");

        //3.定义SQL语句
        String sql = "update account set blance = 2000";

        //4.获取执行 SQL的对象 Statement
        Statement stat = conn.createStatement();

        //5.执行SQL，返回一个int类型的数值，表示了被影响的行数
        int count = stat.executeUpdate(sql);

        //6.处理结果，
        System.out.println(count);
    }
}