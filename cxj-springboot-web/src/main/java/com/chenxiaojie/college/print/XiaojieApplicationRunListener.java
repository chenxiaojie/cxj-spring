package com.chenxiaojie.college.print;

import com.chenxiaojie.college.print.dao.config.DataSourceConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2020-02-19
 * Time: 17:33
 */
public class XiaojieApplicationRunListener implements SpringApplicationRunListener {

    public XiaojieApplicationRunListener(SpringApplication application, String[] args) {
        System.out.println("constructor");
    }

    @Override
    public void starting() {
        System.out.println("starting...");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("environmentPrepared...");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        if (context.getBeanFactory() instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context.getBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceConfiguration.class);
            registry.registerBeanDefinition("xiaojieDataSourceConfiguration", beanDefinitionBuilder.getRawBeanDefinition());
        }
        System.out.println("contextPrepared...");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("contextLoaded...");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("started...");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("running...");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("failed...");
    }
}
