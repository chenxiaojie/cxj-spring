package com.chenxiaojie.spring.feedback.checker;

/**
 * Created by chenxiaojie on 15/10/25.
 */
public interface CanInvokeChecker {
    boolean check(String invokeMethodName, Integer operator, Object dbObject, Object updateObject, Object[] args);
}