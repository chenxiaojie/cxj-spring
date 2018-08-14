package com.chenxiaojie.college.print.biz.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * Created by xiaojie.chen on 2015-03-09 14:21:23.
 */
@Slf4j
@Aspect
@Component
public class ExceptionAop {

    @AfterThrowing(pointcut = "execution(public * com.chenxiaojie.college.print.biz..*.*(..))", throwing = "e")
    public void log(Exception e) {
        log.error("ExceptionAop", e);
    }
}

