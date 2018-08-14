package com.chenxiaojie.college.print.web.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.chen on 18/5/29.
 */
@Slf4j
@Order
@Aspect
@Component
public class PermissionAspect {

    @Around(value = "@annotation(com.chenxiaojie.college.print.web.aop.annotation.PermissionController)")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("PermissionAspect");
        Object retVal = joinPoint.proceed();
        return retVal;
    }
}