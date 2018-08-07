package com.chenxiaojie.spring.namespace.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Xiaojie {

    String value() default "";
}
