package com.chenxiaojie.spring.configuration.thiscallaop;

import com.google.common.collect.Maps;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-08-12
 * Time: 22:01
 */
public class Bootstrap {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = createClass(newEnhancer(AService.class));
        Object o = clazz.newInstance();
        AService aService = (AService) o;
        System.out.println(aService.aop());
        System.out.println(aService.aop());
        System.out.println(aService.aop());
        System.out.println(aService.aop());
        System.out.println(aService.aop());

        System.out.println(aService.noAop());
        System.out.println(aService.noAop());
        System.out.println(aService.noAop());
        System.out.println(aService.noAop());
        System.out.println(aService.noAop());
    }

    private interface EnhancedConfiguration {
    }

    private interface ConditionalCallback extends Callback {
        boolean isMatch(Method candidateMethod);
    }

    private static final Callback[] CALLBACKS = new Callback[]{
            new BeanMethodInterceptor(),
            NoOp.INSTANCE
    };

    private static final ConditionalCallbackFilter CALLBACK_FILTER = new ConditionalCallbackFilter(CALLBACKS);

    private static class ConditionalCallbackFilter implements CallbackFilter {

        private final Callback[] callbacks;

        private final Class<?>[] callbackTypes;

        public ConditionalCallbackFilter(Callback[] callbacks) {
            this.callbacks = callbacks;
            this.callbackTypes = new Class<?>[callbacks.length];
            for (int i = 0; i < callbacks.length; i++) {
                this.callbackTypes[i] = callbacks[i].getClass();
            }
        }

        @Override
        public int accept(Method method) {
            for (int i = 0; i < this.callbacks.length; i++) {
                Callback callback = this.callbacks[i];
                if (!(callback instanceof ConditionalCallback) || ((ConditionalCallback) callback).isMatch(method)) {
                    return i;
                }
            }
            throw new IllegalStateException("No callback available for method " + method.getName());
        }

        public Class<?>[] getCallbackTypes() {
            return this.callbackTypes;
        }
    }

    private static class BeanMethodInterceptor implements MethodInterceptor, ConditionalCallback {

        private Map<Method, Object> cache = Maps.newConcurrentMap();

        @Override
        public boolean isMatch(Method candidateMethod) {
            return candidateMethod.getDeclaringClass() != Object.class && AnnotatedElementUtils.hasAnnotation(candidateMethod, Bean.class);
        }

        @Override
        public Object intercept(Object enhancedConfigInstance, Method beanMethod, Object[] beanMethodArgs, MethodProxy cglibMethodProxy) throws Throwable {
            Object result = cache.get(beanMethod);
            if (result == null) {
                synchronized (cache) {
                    result = cache.get(beanMethod);
                    if (result == null) {
                        result = cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);
                        cache.put(beanMethod, result);
                    }
                }
            }
            return result;
        }
    }


    private static Enhancer newEnhancer(Class<?> configSuperClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(configSuperClass);
        enhancer.setInterfaces(new Class<?>[]{EnhancedConfiguration.class});
        enhancer.setUseFactory(false);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        enhancer.setStrategy(null);
        enhancer.setCallbackFilter(CALLBACK_FILTER);
        enhancer.setCallbackTypes(CALLBACK_FILTER.getCallbackTypes());
        return enhancer;
    }

    private static Class<?> createClass(Enhancer enhancer) {
        Class<?> subclass = enhancer.createClass();
        Enhancer.registerStaticCallbacks(subclass, CALLBACKS);
        return subclass;
    }

}
