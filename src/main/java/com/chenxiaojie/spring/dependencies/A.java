package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@Repository
@DependsOn({"c"})
public class A implements InitializingBean, DisposableBean, ApplicationContextAware, Ordered {

    @Autowired
    public B b;

    @Resource
    public B b2;

    public A() {
        System.out.println("A()");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("A postConstruct");
        System.out.println("A postConstruct,b:" + b);
        System.out.println("A postConstruct,b.a:" + b.a);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("A preDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("A afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("A destroy");
    }

    @Autowired
    public void setAutowired(B b) {
        System.out.println("A setAutowired, b" + b);
    }

    @Resource
    public void setResource(B b) {
        System.out.println("A setResource, b" + b);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("A setApplicationContext");
    }

    @Override
    public int getOrder() {
        System.out.println("A getOrder");
        return HIGHEST_PRECEDENCE;
    }

}
