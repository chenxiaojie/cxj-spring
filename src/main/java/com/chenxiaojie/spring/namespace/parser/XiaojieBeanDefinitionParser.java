package com.chenxiaojie.spring.namespace.parser;

import com.chenxiaojie.spring.namespace.XiaojieContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


public class XiaojieBeanDefinitionParser implements BeanDefinitionParser {

    public static final String BEAN_POST_PROCESSOR_BEAN_NAME = "com.chenxiaojie.spring.namespace.post.processor.xiaojieAnnotationBeanPostProcessor";

    public static final String CONTEXT_BEAN_NAME = "com.chenxiaojie.spring.namespace.xiaojieContext";

    public static final String ID = "id";

    public static final String MAXTIMES = "maxTimes";

    public static final String NAME = "name";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);

        // Register component for the surrounding <xiaojie:annotation-driven> element.
        CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
        parserContext.pushContainingComponent(compDefinition);

        // Nest the concrete post-processor bean in the surrounding component.
        BeanDefinitionRegistry registry = parserContext.getRegistry();

        if (registry.containsBeanDefinition(BEAN_POST_PROCESSOR_BEAN_NAME)) {
            parserContext.getReaderContext().error("Only one XiaojieAnnotationBeanPostProcessor may exist within the context.", source);
        } else {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition("com.chenxiaojie.spring.namespace.post.processor.XiaojieAnnotationBeanPostProcessor");

            builder.getRawBeanDefinition().setSource(source);

            registerPostProcessor(parserContext, builder, BEAN_POST_PROCESSOR_BEAN_NAME);
        }

        // Finally register the composite component.
        parserContext.popAndRegisterContainingComponent();

        registerScheduleManager(element, parserContext);

        return null;
    }


    private void registerScheduleManager(Element element, ParserContext parserContext) {

        BeanDefinitionRegistry registry = parserContext.getRegistry();
        Object source = parserContext.extractSource(element);

        if (registry.containsBeanDefinition(CONTEXT_BEAN_NAME)) {
            parserContext.getReaderContext().error("Only one xiaojieContext may exist within the context.", source);
        } else {
            GenericBeanDefinition managerDefinition = new GenericBeanDefinition();
            managerDefinition.setBeanClass(XiaojieContext.class);

            String id = element.getAttribute(ID);
            if (StringUtils.hasText(id)) {
                managerDefinition.getPropertyValues().add(ID, id);
            }

            String name = element.getAttribute(NAME);
            if (StringUtils.hasText(name)) {
                managerDefinition.getPropertyValues().add(NAME, name);
            }

            String maxTimes = element.getAttribute(MAXTIMES);
            if (StringUtils.hasText(maxTimes)) {
                managerDefinition.getPropertyValues().add(MAXTIMES, NumberUtils.parseNumber(maxTimes, Integer.class));
            }

            BeanDefinitionHolder holder = new BeanDefinitionHolder(managerDefinition, CONTEXT_BEAN_NAME);
            BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
        }
    }

    private void registerPostProcessor(ParserContext parserContext, BeanDefinitionBuilder builder, String beanName) {
        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        parserContext.getRegistry().registerBeanDefinition(beanName, builder.getBeanDefinition());
        BeanDefinitionHolder holder = new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
        parserContext.registerComponent(new BeanComponentDefinition(holder));
    }
}