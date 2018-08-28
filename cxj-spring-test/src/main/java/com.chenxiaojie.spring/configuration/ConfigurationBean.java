package com.chenxiaojie.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    public Boolean isSameBean4() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = configurationBean2.testEntity();
        System.out.println("ConfigurationBean4 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }

    @Bean
    public Boolean isSameBean5() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = this.testEntity1();
        System.out.println("ConfigurationBean4 testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }
}
