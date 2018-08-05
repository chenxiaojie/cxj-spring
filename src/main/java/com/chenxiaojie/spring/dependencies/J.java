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
import java.util.List;

/**
 * Created by chenxiaojie on 2017/1/17.
 */
public class J implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, Ordered, SmartLifecycle, ApplicationListener<ApplicationEvent> {

    public J() {
        System.out.println("J()");
    }

    @Resource
    private A a;

    @Autowired
    private B b;

    @Autowired
    private List<Ordered> orderedList;

    @Resource
    public void setResource(A a) {
        System.out.println("J setResource, a" + a);
    }

    @Autowired
    public void setAutowired(B b) {
        System.out.println("J setAutowired, b" + b);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("J setBeanName:" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("J setApplicationContext:" + applicationContext);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("J postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("J afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("J preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("J destroy");
    }

    //5
    @Override
    public int getOrder() {
        System.out.println("J getOrder");
        return LOWEST_PRECEDENCE;
    }

    //1
    @Override
    public boolean isAutoStartup() {
        System.out.println("J isAutoStartup");
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        System.out.println("J stop Runnable");
    }

    //4
    @Override
    public void start() {
        System.out.println("J build");
    }

    @Override
    public void stop() {
        System.out.println("J stop");
    }

    //3
    @Override
    public boolean isRunning() {
        System.out.println("J isRunning");
        return false;
    }

    //2
    @Override
    public int getPhase() {
        System.out.println("J getPhase");
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("J onApplicationEvent");
        }
    }

}
