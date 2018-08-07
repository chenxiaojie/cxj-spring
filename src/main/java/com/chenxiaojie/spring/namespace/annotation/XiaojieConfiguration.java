package com.chenxiaojie.spring.namespace.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Configuration
@Import(com.chenxiaojie.spring.namespace.registry.XiaojieBeanDefinitionRegistry.class)
public @interface XiaojieConfiguration {

    String id() default "";

    String name() default "";

    int maxTimes() default 500;
}
