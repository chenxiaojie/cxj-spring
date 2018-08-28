package com.chenxiaojie.spring.feedback.aop;

import com.chenxiaojie.spring.feedback.annotation.AccessInterceptor;
import com.chenxiaojie.spring.feedback.checker.AbstractCanInvokeChecker;
import com.chenxiaojie.spring.feedback.checker.CanInvokeChecker;
import com.chenxiaojie.spring.feedback.vo.QuestionVO;
import com.chenxiaojie.spring.feedback.vo.ReplyVO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@Aspect
@Component
public class AccessAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.chenxiaojie.spring.feedback.annotation.AccessInterceptor)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundProcessor(ProceedingJoinPoint pjp) {
        try {
            Signature signature = pjp.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method targetMethod = methodSignature.getMethod();
            Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());

            AccessInterceptor accessInterceptor = AnnotationUtils.getAnnotation(realMethod, AccessInterceptor.class);
            if (accessInterceptor.contextParamIndex() == -1 || accessInterceptor.operatorParamIndex() == -1) {
                throw new RuntimeException("contextParamIndex or operatorParamIndex can not be -1");
            }

            String checkerName = getCheckerName(pjp, accessInterceptor);
            if (!applicationContext.containsBean(checkerName)) {
                System.out.println("警告, checker is null");
                return pjp.proceed();
            }

            CanInvokeChecker checker = (CanInvokeChecker) applicationContext.getBean(checkerName);
            if (checker.check(realMethod.getName(), (Integer) pjp.getArgs()[accessInterceptor.operatorParamIndex()], pjp.getArgs()[accessInterceptor.contextParamIndex()],null, pjp.getArgs())) {
                return pjp.proceed();
            } else {
                throw new RuntimeException("check fail");
            }
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    private String getCheckerName(ProceedingJoinPoint pjp, AccessInterceptor accessInterceptor) {
        String checkName = "";

        if (StringUtils.isNotEmpty(accessInterceptor.checkerName())) {
            checkName = accessInterceptor.checkerName();
        } else {
            Class<?>[] interfaces = pjp.getTarget().getClass().getInterfaces();
            if (interfaces != null && interfaces.length > 0) {
                checkName = interfaces[0].getSimpleName() + "InvokeChecker";
            }
        }

        if (StringUtils.isNotEmpty(checkName)) {
            checkName = checkName.substring(0, 1).toLowerCase() + checkName.substring(1);
        }

        return checkName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
