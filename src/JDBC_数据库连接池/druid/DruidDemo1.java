package JDBC_数据库连接池.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo1 {

    public static void main(String[] args) throws Exception {

        //1.定义配置文件 -> druid.properties
        //2.加载配置文件
        Properties ps = new Properties();
        InputStream is = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        ps.load(is);

        //3.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(ps);

        //4.获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);


    }
}
