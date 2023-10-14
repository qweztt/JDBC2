package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

//JDBC 工具类
public class JDBC_Base_Utils {
    private static String url;
    private static String user;
    private static String password;

    private static String driver;

    static {
        //读取资源文件，获取值

        //1.创建 Properties 集合类
        Properties pro = new Properties();

        try {

            //动态获取src路径下文件的方式    jdbc.properties的路径
            ClassLoader classLoader = JDBC_Base_Utils.class.getClassLoader();

            URL resource = classLoader.getResource("jdbc.properties");

            String path = resource.getPath();

//            System.out.println(path);


            //2.加载文件
            pro.load(new FileReader(path));

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");


            Class.forName(driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection(){

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection conn, Statement stmt){

        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void close(ResultSet rs,Connection conn, Statement stmt){
        if (rs != null){
            try {
                rs.close();
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

        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
