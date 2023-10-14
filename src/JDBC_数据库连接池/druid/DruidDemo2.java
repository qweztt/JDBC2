package JDBC_数据库连接池.druid;

import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DruidDemo2 {

    public static void main(String[] args) {

        /*完成添加操作*/
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO `data`.`account`(`id`, `blance`, `username`) VALUES (null, ?, ?);";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,5000);
            pstmt.setString(2,"张三");

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn,pstmt);
        }


    }
}
