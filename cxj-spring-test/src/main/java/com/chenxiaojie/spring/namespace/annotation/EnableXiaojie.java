package com.chenxiaojie.spring.namespace.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(com.chenxiaojie.spring.namespace.registry.XiaojieBeanDefinitionRegistry.class)
public @interface EnableXiaojie {

    String id() default "";

    String name() default "";

    int maxTimes() default 500;
}
