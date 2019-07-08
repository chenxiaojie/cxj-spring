package com.chenxiaojie.spring.cache;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-08
 * Time: 20:55
 */
public interface IInt {
    int get(int i);

    int put(int i, int j);

    int evict(int i);

    int get2(int i);
}
