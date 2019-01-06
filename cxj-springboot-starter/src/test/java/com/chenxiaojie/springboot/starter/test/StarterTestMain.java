package com.chenxiaojie.springboot.starter.test;

import com.chenxiaojie.springboot.starter.sqlsession.XJSqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.chenxiaojie.springboot.starter")
//@SpringBootConfiguration
public class StarterTestMain {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(StarterTestMain.class, args);
        XJSqlSession xjSqlSession = ac.getBean(XJSqlSession.class);
        xjSqlSession.execute("select * from user");
    }
}
