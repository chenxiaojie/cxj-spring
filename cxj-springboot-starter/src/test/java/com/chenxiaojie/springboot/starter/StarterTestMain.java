//package com.chenxiaojie.springboot.starter;
//
//import com.chenxiaojie.springboot.starter.properties.XJProperties2;
//import com.chenxiaojie.springboot.starter.sqlsession.XJSqlSession;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//
//@SpringBootApplication
//public class StarterTestMain {
//
//    public static void main(String[] args) {
//        ApplicationContext ac = SpringApplication.run(StarterTestMain.class, args);
//        XJSqlSession xjSqlSession = ac.getBean(XJSqlSession.class);
//        xjSqlSession.execute("select * from user");
//
//        XJProperties2 bean = ac.getBean(XJProperties2.class);
//        System.out.println(bean);
//    }
//}
