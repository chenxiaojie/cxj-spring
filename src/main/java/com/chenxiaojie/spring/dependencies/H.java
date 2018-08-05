package com.chenxiaojie.spring.dependencies;


import com.chenxiaojie.spring.dependencies.annotation.CustomBean;

/**
 * Created by chenxiaojie on 2017/1/17.
 */
@CustomBean
public class H {
    public H() {
        System.out.println("H()");
    }
}
