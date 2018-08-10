package com.chenxiaojie.spring.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
public class BootstrapMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationBean.class);
        System.out.println(applicationContext);
    }
}
