package com.chenxiaojie.spring.feedback.checker;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chenxiaojie on 15/10/25.
 */
public abstract class AbstractCanInvokeChecker<T> implements CanInvokeChecker {

    private Class<T> contextClass;

    AbstractCanInvokeChecker() {
        // 获取子类的class(在创建子类对象的时候,会返回父类的构造方法)
        Class<? extends CanInvokeChecker> clazz = this.getClass();
        // 获取当前类的带有泛型的父类类型
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        // 返回实际参数类型(泛型可以写多个)
        Type[] types = type.getActualTypeArguments();
        // 获取第一个参数(泛型的具体类) Person.class
        this.contextClass = (Class<T>) types[0];
    }

    @Override
    public boolean check(String invokeMethodName, Integer operator, Object dbObject, Object updateObject, Object[] args) {
        try {
            Method checkMethod = this.getClass().getDeclaredMethod(invokeMethodName, Integer.class, contextClass,contextClass, args.getClass());
            if (checkMethod == null) {
                System.out.println("警告，没有找到校验方法，method : " + invokeMethodName);
                return true;
            }
            return (Boolean) checkMethod.invoke(this, operator, safeToContextObject(dbObject), updateObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private T safeToContextObject(Object context) {
        if (context == null) {
            return null;
        }

        return toContextObject(context);
    }

    protected abstract T toContextObject(Object context);
}
