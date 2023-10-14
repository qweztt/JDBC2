package JDBC_数据库连接池.JDBCTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JDBCTemplateDemo1 {

    public static void main(String[] args) {
        //创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        //调用方法
        String sql = "UPDATE `data`.`account` SET `blance` = 10000, `username` = '张三' WHERE `id` = ?;";
        template.update(sql,3);

    }
}
