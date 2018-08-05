package com.chenxiaojie.spring.dependencies.bootstrap;

import com.chenxiaojie.spring.dependencies.A;
import com.chenxiaojie.spring.dependencies.B;
import com.chenxiaojie.spring.dependencies.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/appcontext-dependencies.xml"})
public class Bootstrap implements ApplicationContextAware {

    @Autowired
    private A a;

    @Autowired
    private B b;

    @Autowired
    private C c;

    private ApplicationContext applicationContext;

    @Test
    public void test() {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

//        J j = applicationContext.getBean(J.class);
//        System.out.println(j);
//
//        K k = applicationContext.getBean(K.class);
//        System.out.println(k);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
