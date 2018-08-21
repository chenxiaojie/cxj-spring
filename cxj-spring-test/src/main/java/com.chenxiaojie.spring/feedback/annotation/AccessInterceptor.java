package com.chenxiaojie.spring.feedback.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessInterceptor {

    int contextParamIndex() default -1;

    int operatorParamIndex() default -1;

    String checkerName() default "";
}