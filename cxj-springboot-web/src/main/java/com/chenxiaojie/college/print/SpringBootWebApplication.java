package com.chenxiaojie.college.print;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@Slf4j
//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableTransactionManagement
@MapperScan(basePackages = "com.chenxiaojie.college.print.dao.api")
public class SpringBootWebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootWebApplication.class);
        application.setAdditionalProfiles("test");
        ApplicationContext ctx = application.run(args);
//        ApplicationContext ctx = SpringApplication.run(SpringBootWebApplication.class, args);
        log.info("ctx started, ctx : " + ctx);

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
