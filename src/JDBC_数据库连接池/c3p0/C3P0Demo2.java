package JDBC_数据库连接池.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo2 {

    public static void main(String[] args) throws SQLException {
        //1.获取DataSource对象，使用默认配置
        DataSource ds = new ComboPooledDataSource();

        //2.获取连接 - - -最大参数验证
        for (int i = 0; i < 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(conn);

            if( i == 4){
                conn.close();
            }
        }
    }

}
