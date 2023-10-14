package JDBC_BaseKnowledge;

import utils.JDBC_Base_Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo7 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from account";
            conn = JDBC_Base_Utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("blance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
