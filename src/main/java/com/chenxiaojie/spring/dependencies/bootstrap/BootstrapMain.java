package com.chenxiaojie.spring.dependencies.bootstrap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
public class BootstrapMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:/appcontext-dependencies.xml");
        System.out.println(classPathXmlApplicationContext);
    }
}
