package com.chenxiaojie.spring.namespace.post.processor;

import com.chenxiaojie.spring.namespace.annotation.Xiaojie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class XiaojieAnnotationBeanPostProcessor implements BeanPostProcessor, Ordered {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Set<Class<?>> nonAnnotatedClasses = Collections.newSetFromMap(new ConcurrentHashMap<Class<?>, Boolean>(64));

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        if (!this.nonAnnotatedClasses.contains(targetClass)) {
            final Set<Method> annotatedMethods = new LinkedHashSet<Method>(1);
            ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
                @Override
                public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                    Xiaojie xiaojie = AnnotationUtils.getAnnotation(method, Xiaojie.class);
                    if (xiaojie != null) {
                        processXiaojie(xiaojie, method, bean);
                        annotatedMethods.add(method);
                    }
                }
            });
            if (annotatedMethods.isEmpty()) {
                this.nonAnnotatedClasses.add(targetClass);
                if (logger.isTraceEnabled()) {
                    logger.trace("No @Xiaojie annotations found on bean class: " + bean.getClass());
                }
            } else {
                // Non-empty set of methods
                if (logger.isDebugEnabled()) {
                    logger.debug(annotatedMethods.size() + " @Xiaojie methods processed on bean '" + beanName + "': " + annotatedMethods);
                }
            }
        }
        return bean;
    }

    private void processXiaojie(Xiaojie scheduled, Method method, Object bean) {
        try {
            Class<?>[] parameterTypes = method.getParameterTypes();

            int parameterLength = parameterTypes.length;
            Assert.isTrue(void.class == method.getReturnType(), "Only void-returning methods may be annotated with @Xiaojie");
            Assert.isTrue(parameterLength < 2, "Only no-arg or one-arg methods may be annotated with @Xiaojie");

            if (AopUtils.isJdkDynamicProxy(bean)) {
                try {
                    // Found a @Xiaojie method on the target class for this JDK proxy ->
                    // is it also present on the proxy itself?
                    method = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                } catch (SecurityException ex) {
                    ReflectionUtils.handleReflectionException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new IllegalStateException(String.format(
                            "@Xiaojie method '%s' found on bean target class '%s' but not " +
                                    "found in any interface(s) for a dynamic proxy. Either pull the " +
                                    "method up to a declared interface or switch to subclass (CGLIB) " +
                                    "proxies by setting proxy-target-class/proxyTargetClass to 'true'",
                            method.getName(), method.getDeclaringClass().getSimpleName()));
                }
            } else if (AopUtils.isCglibProxy(bean)) {
                // Common problem: private methods end up in the proxy instance, not getting delegated.
                if (Modifier.isPrivate(method.getModifiers())) {
                    throw new IllegalStateException(String.format(
                            "@Xiaojie method '%s' found on CGLIB proxy for target class '%s' but cannot " +
                                    "be delegated to target bean. Switch its visibility to package or protected.",
                            method.getName(), method.getDeclaringClass().getSimpleName()));
                }
            }

            String taskName = scheduled.value();
            if (StringUtils.hasText(taskName)) {
                System.out.println("find task:" + taskName);
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Encountered invalid @Xiaojie method '" + method.getName() + "': " + ex.getMessage(), ex);
        }
    }
}