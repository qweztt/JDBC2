package JDBC_BaseKnowledge;

import java.sql.*;

//结果集对象，封装查询结果 - - - > ResultSet
public class JDBCDemo6 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String sql = "select * from account";

            conn = DriverManager.getConnection("jdbc:mysql:///data","root","root");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getString("blance"));
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

            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
