package JDBC_BaseKnowledge;

import utils.JDBC_Base_Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCDemo9 {

    public static void main(String[] args) {
//        UPDATE `data`.`account` SET `blance` = 2500, `username` = 'xiaoming' WHERE `id` = 2;

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;


        try {
            conn = JDBC_Base_Utils.getConnection();
            conn.setAutoCommit(false);

            String sql1 = "UPDATE `data`.`account` SET `blance` = blance - ? WHERE `id` = ?;";
            String sql2 = "UPDATE account SET blance = blance + ? WHERE id = ?;";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);

            pstmt1.setInt(1,500);
            pstmt1.setInt(2,1);

            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);

            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

//            System.out.println("执行成功");
            conn.commit();
        } catch (SQLException e) {

            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        } finally {
            JDBC_Base_Utils.close(conn,pstmt1);
            JDBC_Base_Utils.close(null,pstmt2);
        }



    }


}
