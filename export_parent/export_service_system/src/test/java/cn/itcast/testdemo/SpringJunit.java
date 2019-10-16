package cn.itcast.testdemo;

import cn.itcast.dao.cargo.FactoryDao;
import cn.itcast.domain.cargo.Factory;
import cn.itcast.domain.cargo.FactoryExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-*.xml")
public class SpringJunit {

    @Autowired
    private FactoryDao factoryDao;

    //全查
    @Test
    public void test1(){
        List<Factory> list = factoryDao.selectByExample(null);
        for (Factory factory : list) {
            System.out.println(factory);
        }
    }


    //条件查
        // 1 创建FactoryExample对象
        // 2 创建criteria对象
        // 3 设置条件
        // 4 执行
    @Test
    public void test2(){
        // 1 创建FactoryExample对象
        FactoryExample factoryExample = new FactoryExample();
        // 2 创建criteria对象
        FactoryExample.Criteria criteria = factoryExample.createCriteria();
        // 3 设置条件
        criteria.andFactoryNameLike("%天%");
        criteria.andContactsEqualTo("渠海");
        // 4 执行
        List<Factory> list = factoryDao.selectByExample(factoryExample);
        for (Factory factory : list) {
            System.out.println(factory);
        }
    }
}
