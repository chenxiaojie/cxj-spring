package com.chenxiaojie.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentBean {

    @Bean("ComponentBeanTestEntity")
    public TestEntity testEntity() {
        return new TestEntity();
    }

    @Bean("ComponentBeanIsSameBean")
    public Boolean isSameBean() {
        TestEntity testEntity1 = this.testEntity();
        TestEntity testEntity2 = this.testEntity();
        System.out.println("ComponentBean testEntity1 == testEntity2 : " + (testEntity1 == testEntity2));
        return testEntity1 == testEntity2;
    }

}
