package JDBC_BaseKnowledge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//删除一条记录
public class JDBCDemo4 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            String sql = "delete from account where username = 'root'";
            //1.连接驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.连接 MySQL数据库
            conn = DriverManager.getConnection("jdbc:mysql:///data","root","root");

            //3.获取执行MySQL语句的对象(DML语句)常用于 Data Manipulation Language
            //增/删/改  insert/delete/update
            stmt = conn.createStatement();

            //4.执行SQL语句
            int count = stmt.executeUpdate(sql);
            if (count > 0){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

}
