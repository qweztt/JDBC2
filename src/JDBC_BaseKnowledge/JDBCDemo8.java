package JDBC_BaseKnowledge;// 使用数据库来模拟用户登录

import utils.JDBC_Base_Utils;

import java.sql.*;
import java.util.Scanner;

public class JDBCDemo8 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("请输入登录账号：");
        String loginName = sc.nextLine();
        System.out.print("请输入登录密码：");
        String loginPwd = sc.nextLine();

        if (JDBCDemo8.isLogin2(loginName,loginPwd)){
            System.out.println("登录成功");
        } else {
            System.out.println("登陆失败");
        }
    }


    public static boolean isLogin2(String uName,String pwd){
        Connection conn = null;
        PreparedStatement  pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT\n" +
                    "login.username,\n" +
                    "login.`password`\n" +
                    "FROM\n" +
                    "login\n where username = ? and password = ?" ;
            conn = JDBC_Base_Utils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,uName);
            pstmt.setString(2,pwd);

            rs = pstmt.executeQuery();
            while (rs.next()){
                 /*System.out.println(rs.getString("username"));
                 System.out.println(rs.getString("password"));*/
                String username = rs.getString("username");
                String password = rs.getString("password");

                if (username.equals(uName) && password.equals(pwd)){
                    return true;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBC_Base_Utils.close(rs,conn,pstmt);
        }
        return false;
    }

    public static boolean isLogin(String uName,String pwd){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT\n" +
                    "login.username,\n" +
                    "login.`password`\n" +
                    "FROM\n" +
                    "login\n";
            conn = JDBC_Base_Utils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                 /*System.out.println(rs.getString("username"));
                 System.out.println(rs.getString("password"));*/
                String username = rs.getString("username");
                String password = rs.getString("password");

                if (username.equals(uName) && password.equals(pwd)){
                    return true;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBC_Base_Utils.close(rs,conn,stmt);
        }
        return false;
    }
}
