package com.chenxiaojie.spring.lazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@EnableAsync
@Configuration
@ComponentScan("com.chenxiaojie.spring.lazy")
public class BootstrapMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BootstrapMain.class);
        System.out.println(applicationContext);

        LazyBean bean = applicationContext.getBean(LazyBean.class);
        bean.lazy();
        bean.lazy2();

        applicationContext.close();
    }
}
