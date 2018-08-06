package com.chenxiaojie.spring.dependencies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

/**
 * Created by chenxiaojie on 2017/1/17.
 */
@Repository
public class I extends PropertyPlaceholderConfigurer implements BeanFactoryAware, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, ApplicationListener<ApplicationEvent> {

    @Autowired
    private A a;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;

    public I() {
        System.out.println("I()");
    }

    @Autowired
    public void setAutowired(A a) {
        System.out.println("I setAutowired 不会被执行");
        System.out.println("I setAutowired, a" + a);
    }

    @Override
    public void setBeanName(String beanName) {
        super.setBeanName(beanName);
        System.out.println("I setBeanName");
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        System.out.println("I:props.size() = " + props.size());
        super.processProperties(beanFactoryToProcess, props);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("I setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("I setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("I postConstruct 不会被执行");
        System.out.println("I postConstruct, a:" + a);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("I afterPropertiesSet, a:" + a);
        //J j = new J();
        //((ConfigurableListableBeanFactory) beanFactory).registerSingleton("j", j);

        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(J.class);
        defaultListableBeanFactory.registerBeanDefinition("j", beanDefinitionBuilder.getRawBeanDefinition());

        //        提前获取则会导致生命周期提前缺失很多PostProcessor处理器
        //        J j = applicationContext.getBean(J.class);
        //        System.out.println("I onApplicationEvent, j:" + j);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("I destroy");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("I preDestroy");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
//            J j = applicationContext.getBean(J.class);
//            System.out.println("I onApplicationEvent, j:" + j);
        }
    }
}
