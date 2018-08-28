package com.chenxiaojie.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean2 {
    @Bean
    public TestEntity testEntity() {
        return new TestEntity();
    }
}
