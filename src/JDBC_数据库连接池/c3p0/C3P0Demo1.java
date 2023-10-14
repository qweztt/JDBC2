package JDBC_数据库连接池.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//c3p0基础测试
public class C3P0Demo1 {

    public static void main(String[] args) throws SQLException {

        DataSource ds = new ComboPooledDataSource();
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }


}
