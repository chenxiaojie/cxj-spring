package com.gc.college.print.dao.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xiaojie.chen on 2018-03-09.
 * 不能拦截jdk proxy interface
 */
@Slf4j
@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DAOAop {

    //@Pointcut("execution(* com.gc.college.print.dao.api..*.*(..))")
    @Pointcut("@annotation(com.gc.college.print.dao.aop.DAOAnnotation)")
    private void pointcut() {
    }//定义一个切入点,方法不需要实现

    @Before("pointcut() && args(name)")
    public void beforeProcessor(JoinPoint joinPoint, Object name) {
        log.info(joinPoint + "前置通知:" + name);
    }

    @After("pointcut()")
    public void afterProcessor() {
        log.info("后置通知");
    }

    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void finalProcessor(Object returnValue) {
        log.info("返回通知:" + returnValue);
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void errorProcessor(Exception ex) {
        log.info("异常通知" + ex);
    }

    @Around("pointcut()")
    public Object aroundProcessor(ProceedingJoinPoint pjp) {
        log.info("进入环绕通知");
        Object object = null;//执行该方法
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
        }
        log.info("退出环绕通知");
        return object;
    }
}
