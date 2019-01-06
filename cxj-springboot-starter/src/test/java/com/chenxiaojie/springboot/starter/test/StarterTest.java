package com.chenxiaojie.springboot.starter.test;

import com.chenxiaojie.springboot.starter.sqlsession.XJSqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarterTest.class)
@ComponentScan(basePackages = "com.chenxiaojie.springboot.starter")
public class StarterTest implements ApplicationContextAware {

    @Autowired
    private XJSqlSession sqlSession;

    @Test
    public void test() {
        sqlSession.execute("select * from user");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}
