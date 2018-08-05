package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by chenxiaojie on 2017/1/17.
 */
public class K implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, Ordered, SmartLifecycle, ApplicationListener<ApplicationEvent> {

    public K() {
        System.out.println("K()");
    }

    @Resource
    private A a;

    @Autowired
    private B b;

    @Resource
    public void setResource(A a) {
        System.out.println("K setResource, a" + a);
    }

    @Autowired
    public void setAutowired(B b) {
        System.out.println("K setAutowired, b" + b);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("K setBeanName:" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("K setApplicationContext:" + applicationContext);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("K postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("K afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("K preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("K destroy");
    }

    //5
    @Override
    public int getOrder() {
        System.out.println("K getOrder");
        return LOWEST_PRECEDENCE;
    }

    //1
    @Override
    public boolean isAutoStartup() {
        System.out.println("K isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("K stop Runnable");
    }

    //4
    @Override
    public void start() {
        System.out.println("K build");
    }

    @Override
    public void stop() {
        System.out.println("K stop");
    }

    //3
    @Override
    public boolean isRunning() {
        System.out.println("K isRunning");
        return false;
    }

    //2
    @Override
    public int getPhase() {
        System.out.println("K getPhase");
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("K onApplicationEvent");
        }
    }

}
