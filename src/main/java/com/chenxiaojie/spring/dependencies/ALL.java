package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by chenxiaojie on 2017/1/17.
 */
@Repository
public class ALL implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, SmartLifecycle, ApplicationListener<ApplicationEvent> {

    public ALL() {
        System.out.println("ALL()");
    }

    @Resource
    private A a;

    @Autowired
    private B b;

    @Resource
    public void setResource(A a) {
        System.out.println("ALL setResource, a" + a);
    }

    @Autowired
    public void setAutowired(B b) {
        System.out.println("ALL setAutowired, b" + b);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("ALL setBeanName:" + s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("ALL setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ALL setApplicationContext");
    }


    @PostConstruct
    public void postConstruct() {
        System.out.println("ALL postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ALL afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("ALL preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("ALL destroy");
    }

    //1
    @Override
    public boolean isAutoStartup() {
        System.out.println("ALL isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("ALL stop Runnable");
    }

    //4
    @Override
    public void start() {
        System.out.println("ALL build");
    }

    @Override
    public void stop() {
        System.out.println("ALL stop");
    }

    //3
    @Override
    public boolean isRunning() {
        System.out.println("ALL isRunning");
        return false;
    }

    //2
    @Override
    public int getPhase() {
        System.out.println("ALL getPhase");
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("ALL onApplicationEvent");
        }
    }
}
