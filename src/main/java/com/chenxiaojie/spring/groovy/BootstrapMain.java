package com.chenxiaojie.spring.groovy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class BootstrapMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:appcontext-groovy.groovy");
        CustomBean customBean = (CustomBean) ctx.getBean(CustomBean.class);
        System.out.println(customBean);
    }
}
