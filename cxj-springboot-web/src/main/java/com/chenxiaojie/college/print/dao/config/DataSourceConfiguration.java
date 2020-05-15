package com.chenxiaojie.college.print.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-12-11
 * Time: 22:02
 */
//@Configuration("xiaojieDataSourceConfiguration")
public class DataSourceConfiguration implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registerDataSource(registry);
        registerSqlSessionFactoryBean(registry);
        registerTransactionManager(registry);
    }

    private void registerDataSource(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(HikariDataSource.class);

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.add("driverClassName", "com.mysql.jdbc.Driver");
        mutablePropertyValues.add("jdbcUrl", "jdbc:mysql://localhost:3306/GC_Print?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false");
        mutablePropertyValues.add("username", "root");
        mutablePropertyValues.add("password", "qwe888888");

        beanDefinitionBuilder.getBeanDefinition().setPropertyValues(mutablePropertyValues);
        registry.registerBeanDefinition("rearDataSource", beanDefinitionBuilder.getRawBeanDefinition());
    }

    private void registerSqlSessionFactoryBean(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SqlSessionFactoryBean.class);

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        RuntimeBeanReference rearDataSource = new RuntimeBeanReference("rearDataSource");
        mutablePropertyValues.add("dataSource", rearDataSource);
        beanDefinitionBuilder.getBeanDefinition().setPropertyValues(mutablePropertyValues);

        registry.registerBeanDefinition("rearSqlSessionFactoryBean", beanDefinitionBuilder.getRawBeanDefinition());
    }


    private void registerTransactionManager(BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(DataSourceTransactionManager.class);

        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        RuntimeBeanReference rearDataSource = new RuntimeBeanReference("rearDataSource");
        mutablePropertyValues.add("dataSource", rearDataSource);
        beanDefinitionBuilder.getBeanDefinition().setPropertyValues(mutablePropertyValues);

        registry.registerBeanDefinition("rearTransactionManager", beanDefinitionBuilder.getRawBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
