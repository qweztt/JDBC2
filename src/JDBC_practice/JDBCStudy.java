package JDBC_practice;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;

public class JDBCStudy {

   JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //将id为1的员工工资设为1w元
    @Test
    public void test1(){
        String sql = "update employees set salary = 10000 where id = 1 ";
        System.out.println(template.update(sql));
    }

    //添加一条记录
    @Test
    public void test2(){
        String sql = "INSERT INTO employees (`id`, `ename`, `salary`) VALUES (?,?,?);";
        template.update(sql,21,"111",2000);
    }

    @Test
    public void test3(){
        String sql = "delete from employees where id = ?";
        template.update(sql,21);
    }
}
