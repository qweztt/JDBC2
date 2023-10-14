package JDBC_BaseKnowledge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
*    account添加一条记录 insert语句
*
*
* */
public class JDBCDemo2 {


public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    try {
        //1.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.定义SQL语句
        String sql = "insert into account values (5000,'root')";
        //3.获取Connection对象
        conn = DriverManager.getConnection("jdbc:mysql:///data", "root", "root");
        //4.获取执行sql语句的对象 Statement对象
         stmt = conn.createStatement();
         //5.执行sql语句
        int count = stmt.executeUpdate(sql);//影响的行数
        //6.处理结果
        System.out.println(count);

        if (count>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }


    } catch (Exception e){
        e.printStackTrace();
    }
    //7.释放资源
    finally {
        //避免空指针异常
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

}
