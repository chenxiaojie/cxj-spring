package com.chenxiaojie.springboot.starter.test;

import com.chenxiaojie.springboot.starter.sqlsession.XJSqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@Configuration
//@ComponentScan("com.chenxiaojie.springboot.starter")
public class StarterTestMain {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(StarterTestMain.class, args);
        XJSqlSession xjSqlSession = ac.getBean(XJSqlSession.class);
        xjSqlSession.execute("select * from user");
    }
}
