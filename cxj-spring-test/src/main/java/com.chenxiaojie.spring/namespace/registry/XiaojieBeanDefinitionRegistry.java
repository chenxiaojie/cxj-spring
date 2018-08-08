package com.chenxiaojie.spring.namespace.registry;

import com.chenxiaojie.spring.namespace.XiaojieContext;
import com.chenxiaojie.spring.namespace.annotation.EnableXiaojie;
import com.chenxiaojie.spring.namespace.parser.XiaojieBeanDefinitionParser;
import com.chenxiaojie.spring.namespace.post.processor.XiaojieAnnotationBeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ObjectUtils;

import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class XiaojieBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(XiaojieBeanDefinitionRegistry.class);

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        if (this.beanFactory == null) {
            logger.warn("It is better to use XiaojieConfiguration WITHOUT any xml configuration file, injected by @Configuration or Spring Boot purely");
            if (!registry.containsBeanDefinition(XiaojieBeanDefinitionParser.BEAN_POST_PROCESSOR_BEAN_NAME)) {
                registry.registerBeanDefinition(XiaojieBeanDefinitionParser.BEAN_POST_PROCESSOR_BEAN_NAME, new RootBeanDefinition(XiaojieAnnotationBeanPostProcessor.class));
            }
            if (!registry.containsBeanDefinition(XiaojieBeanDefinitionParser.CONTEXT_BEAN_NAME)) {
                registerScheduleManager(importingClassMetadata, registry);
            }
            return;
        }

        if (ObjectUtils.isEmpty(this.beanFactory.getBeanNamesForType(XiaojieAnnotationBeanPostProcessor.class, true, false))) {
            registry.registerBeanDefinition(XiaojieBeanDefinitionParser.BEAN_POST_PROCESSOR_BEAN_NAME, new RootBeanDefinition(XiaojieAnnotationBeanPostProcessor.class));
        }

        if (ObjectUtils.isEmpty(this.beanFactory.getBeanNamesForType(XiaojieContext.class, true, false))) {
            registerScheduleManager(importingClassMetadata, registry);
        }

    }

    private void registerScheduleManager(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        GenericBeanDefinition managerDefinition = new GenericBeanDefinition();
        managerDefinition.setBeanClass(XiaojieContext.class);
        boolean isAnnotated = importingClassMetadata.isAnnotated(EnableXiaojie.class.getName());

        if (isAnnotated) {
            Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableXiaojie.class.getName());
            if (attributes != null && !attributes.isEmpty()) {
                Object id = attributes.get(XiaojieBeanDefinitionParser.ID);
                if (id != null) {
                    managerDefinition.getPropertyValues().add(XiaojieBeanDefinitionParser.ID, id);
                }

                Object name = attributes.get(XiaojieBeanDefinitionParser.NAME);
                if (name != null) {
                    managerDefinition.getPropertyValues().add(XiaojieBeanDefinitionParser.NAME, name);
                }

                Object maxTimes = attributes.get(XiaojieBeanDefinitionParser.MAXTIMES);
                if (maxTimes != null) {
                    managerDefinition.getPropertyValues().add(XiaojieBeanDefinitionParser.MAXTIMES, maxTimes);
                }
            }
        }

        BeanDefinitionHolder holder = new BeanDefinitionHolder(managerDefinition, XiaojieBeanDefinitionParser.CONTEXT_BEAN_NAME);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

}
