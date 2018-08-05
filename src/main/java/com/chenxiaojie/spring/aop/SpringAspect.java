package com.chenxiaojie.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@Aspect
@Component
public class SpringAspect {

    @Pointcut("!execution(* com.chenxiaojie..aop.spring..introduction..*.*(..))" +
            " and " +
            "execution(* com.chenxiaojie..aop.spring..*.*(..))")
    private void pointcut() {
    }//定义一个切入点,方法不需要实现

    @Before("pointcut() && args(name)")
    public void beforeProcessor(JoinPoint joinPoint, String name) {
        System.out.println(joinPoint + "前置通知:" + name);
    }

    @After("pointcut()")
    public void afterProcessor() {
        System.out.println("后置通知");
    }

    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void finalProcessor(Object returnValue) {
        System.out.println("返回通知:" + returnValue);
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void errorProcessor(Exception ex) {
        System.out.println("异常通知" + ex);
    }

    @Around("pointcut()")
    public Object aroundProcessor(ProceedingJoinPoint pjp) {
        System.out.println("进入环绕通知");
        Object object = null;//执行该方法
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        System.out.println("退出环绕通知");
        return object;
    }
}
