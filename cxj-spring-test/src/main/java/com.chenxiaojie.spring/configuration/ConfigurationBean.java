package com.chenxiaojie.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan("com.chenxiaojie.spring.configuration.bootstrap")
@Import(ComponentBean.class)
public class ConfigurationBean {

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
    public Boolean isSameBean() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = this.testEntity();
        System.out.println("ConfigurationBean1 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean2() {
        TestEntity testEntity1 = this.testEntity1();
        TestEntity testEntity2 = this.testEntity2();
        System.out.println("ConfigurationBean2 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }
}
