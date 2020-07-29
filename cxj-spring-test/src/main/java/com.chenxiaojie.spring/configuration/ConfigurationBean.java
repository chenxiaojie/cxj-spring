package com.chenxiaojie.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Configuration
@ComponentScan("com.chenxiaojie.spring.configuration")
//@Import(ComponentBean.class)
public class ConfigurationBean {

    @Autowired
    private ComponentBean componentBean;

    @Autowired
    private ConfigurationBean2 configurationBean2;

    @Bean
    public TestEntity testEntity() {
        return new TestEntity();
    }

    @Bean("ConfigurationBeanTestEntity")
    public TestEntity testEntity1() {
        return new TestEntity();
    }

    @Bean("ConfigurationBeanTestEntity")
    public TestEntity testEntity2() {
        return new TestEntity();
    }

    @Bean
    public Boolean isSameBean1(@Qualifier("testEntity") TestEntity testEntity3) {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = this.testEntity();
        System.out.println("ConfigurationBean1 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        System.out.println("ConfigurationBean1 testEntity1 == testEntity3 : " + (testEntity1 == testEntity3));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean2() {
        TestEntity testEntity1 = this.testEntity1();
        TestEntity testEntity2 = this.testEntity2();
        System.out.println("ConfigurationBean2 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean3() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = componentBean.testEntity();
        System.out.println("ConfigurationBean3 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean4() throws ExecutionException, InterruptedException {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = configurationBean2.testEntity();
        Future<TestEntity> future = configurationBean2.testEntity2();
        TestEntity testEntity3 = future.get();
        System.out.println("ConfigurationBean4 " + configurationBean2);
        System.out.println("ConfigurationBean4 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        System.out.println("ConfigurationBean4 testEntity2 == testEntity3 : " + (testEntity2 == testEntity3));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean5() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = this.testEntity1();
        System.out.println("ConfigurationBean5 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }
}
