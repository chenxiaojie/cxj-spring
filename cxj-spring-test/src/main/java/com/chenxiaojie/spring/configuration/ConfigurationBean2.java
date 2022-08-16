package com.chenxiaojie.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.Future;

@EnableAsync
@Configuration
public class ConfigurationBean2 {

    @Lazy
    @Autowired
    private ConfigurationBean2 configurationBean2;

    @Autowired
    private ComponentBean componentBean;

    public void printComponentBean() {
        System.out.println("ConfigurationBean2 printComponentBean " + componentBean.testEntity());
    }

    @Bean
    public TestEntity testEntity() {
        return new TestEntity();
    }

    @Async
    public Future<TestEntity> testEntity2() {
        System.out.println("ConfigurationBean2 public Future<TestEntity> testEntity2:" + (this == configurationBean2));
        this.printComponentBean();
        configurationBean2.printComponentBean();
        return new AsyncResult<>(new TestEntity());
    }

    @Bean
    public TaskExecutor myExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("parseMyTask");
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(30);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ConfigurationBean2 destroy()");
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) this.myExecutor();
        taskExecutor.destroy();
    }
}
