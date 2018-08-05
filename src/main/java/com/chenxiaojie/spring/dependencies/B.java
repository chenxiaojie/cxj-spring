package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@Repository
public class B implements BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware, Ordered {

    @Autowired
    public A a;

    @Resource
    public A a2;

    public B() {
        System.out.println("B()");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("B:" + s);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("B postConstruct");
        System.out.println("B postConstruct,a:" + a);
        System.out.println("B postConstruct,a.b:" + a.b);
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("B preDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("B afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("B destroy");
    }

    @Autowired
    public void setAutowired(A a) {
        System.out.println("B setAutowired, a" + a);
    }

    @Resource
    public void setResource(A a) {
        System.out.println("B setResource, a" + a);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("B setApplicationContext");
    }

    @Override
    public int getOrder() {
        System.out.println("B getOrder");
        return LOWEST_PRECEDENCE;
    }
}
