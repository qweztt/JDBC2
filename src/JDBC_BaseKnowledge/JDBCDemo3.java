package JDBC_BaseKnowledge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//account表修改一条记录
public class JDBCDemo3 {


    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE account SET blance = 2500 WHERE username = 'xiaoming'";

        try {
            conn = DriverManager.getConnection("jdbc:mysql:///data","root","root");
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);

            if (count > 0){
                System.out.println("修改成功");
            }
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
