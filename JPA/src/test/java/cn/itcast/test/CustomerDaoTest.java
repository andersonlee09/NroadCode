package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 保存客户：调用save(obj)方法
     */
    @Test
    public void testSave() {
        Customer c = new Customer();
        Stack<Integer> stack = new Stack<>();
        c.setCustName("传智播客");
        customerDao.save(c);
    }

    /**
     * 修改客户：调用save(obj)方法
     *      对于save方法的解释：如果执行此方法是对象中存在id属性，即为更新操作会先根据id查询，再更新
     *                      如果执行此方法中对象中不存在id属性，即为保存操作
     *
     */
    @Test
    public void testUpdate() {
        //根据id查询id为1的客户
        Customer customer = customerDao.findById(1l).get();
        //修改客户名称
        customer.setCustName("传智播客顺义校区");
        //更新
        customerDao.save(customer);


    }

    /**
     * 根据id删除：调用delete(id)方法
     */
    @Test
    public void testDelete() {
        customerDao.deleteById(1L);
    }

    /**
     * 根据id查询：调用findOne(id)方法
     */
    @Test
    public void testFindById() {
        Customer customer = customerDao.findById(1L).get();
        System.out.println(customer);
    }
}
