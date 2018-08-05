package com.chenxiaojie.spring.aop.introduction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/appcontext-aop-introduction.xml"})
public class Bootstrap implements ApplicationContextAware{

    @Resource
    private OneInterface one;

    @Resource
    private TwoInterface two;

    @Test
    public void test() {
        one.one("-test");
        ((TwoInterface) one).two("-test");

        two.two("-test");
        ((OneInterface) two).one("-test");

        System.out.println("------------------");

        one.same("-test");
        two.same("-test");

        ((OneInterface) two).same("-test");
        ((TwoInterface) one).same("-test");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}
