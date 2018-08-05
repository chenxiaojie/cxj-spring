package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by chenxiaojie on 2016/11/26.
 */
@Service
public class D implements BeanPostProcessor, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, Ordered {

    @Autowired
    private A a;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    public D() {
        System.out.println("D()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("D setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("D applicationContext");
        this.applicationContext = applicationContext;

        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();


        if (beanFactory == defaultListableBeanFactory) {
            System.out.println("D beanFactory == defaultListableBeanFactory");
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(K.class);
        defaultListableBeanFactory.registerBeanDefinition("k", beanDefinitionBuilder.getRawBeanDefinition());
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("D postConstruct, a:" + a);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("D afterPropertiesSet, a:" + a);
        K k = applicationContext.getBean(K.class);
        //AutowireUtils.autowire(k, applicationContext);
        System.out.println("D afterPropertiesSet, k:" + k);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("D destroy");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("D preDestroy");
    }

    @Override
    public int getOrder() {
        System.out.println("D getOrder");
        System.out.println("D getOrder, a:" + a);
        return LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        //System.out.println("D postProcessBeforeInitialization");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        //System.out.println("D postProcessAfterInitialization");
        return o;
    }

//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println("D postProcessBeanFactory");
//
//        K k = applicationContext.getBean(K.class);
//        System.out.println("D afterPropertiesSet, k:" + k);
//    }
}