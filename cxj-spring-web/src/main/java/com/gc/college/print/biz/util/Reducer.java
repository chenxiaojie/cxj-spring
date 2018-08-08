package com.gc.college.print.biz.util;

/**
 * Created by chenxiaojie on 2016/12/17.
 */
public interface Reducer<F, T> {
    T apply(T pre, F cur);
}