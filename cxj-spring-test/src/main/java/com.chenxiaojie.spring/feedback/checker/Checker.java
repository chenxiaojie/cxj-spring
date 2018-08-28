package com.chenxiaojie.spring.feedback.checker;

/**
 * Created by chenxiaojie on 15/10/25.
 */
public interface Checker<T> {
    boolean check(Integer operator, T dbObject, T updateObject, Object[] args);
}