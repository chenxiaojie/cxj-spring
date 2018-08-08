package com.gc.college.print.dao.aop;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DAOAnnotation {
}
