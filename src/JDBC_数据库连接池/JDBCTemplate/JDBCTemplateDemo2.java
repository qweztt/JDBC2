package JDBC_数据库连接池.JDBCTemplate;

import domain.Emp;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;
import java.util.Map;

public class JDBCTemplateDemo2 {
    //Update ---> DML
    //1.获取JDBCTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //Junit单元测试，可以让方法独立执行
    //将id为1的员工工资设为1w元
    @Test
    public void test1() {
        String sql = "update employees set salary = 10000 where id = 1 ";
        template.update(sql);
    }

    //添加一条记录
    @Test
    public  void  test2(){
        String sql = "INSERT INTO employees (`id`, `ename`, `salary`) VALUES (?,?,?);";
        template.update(sql,21,"张三",1499);
    }

    //删除刚刚添加的记录
    @Test
    public void test3(){
        String sql = "delete from employees where id = ?";
        template.update(sql,21);
    }

    //查询id为1的记录，将其封装为Map集合
    //这个方法查询的结果集长度只能是 1 ，将列名作为key，将值名作为 value，把这条记录封装为一个map
    @Test
    public void test4(){
        String sql = "select * from employees where id = ?";
        Map<String,Object> map =  template.queryForMap(sql,1);
        System.out.println(map);
    }

    //查询所有数据
    //将每一条记录封装为一个map集合，再将map集合装载到List集合中
    @Test
    public void test5(){
        String sql = "select * from employees";
        List<Map<String, Object>> list = template.queryForList(sql);

//        System.out.println(list.get(0));
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void test6(){
        String sql = "select * from employees";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : list) {
            System.out.println(emp);
        }

    }
}
