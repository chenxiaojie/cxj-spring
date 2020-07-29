package com.chenxiaojie.spring.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2020-07-29
 * Time: 16:23
 */
@Component
public class SimpleBean {

    @Autowired
    private DependOneBean dependOneBean;

    public void simple() {
        System.out.println("SimpleBean call simple()");
    }

    public void callme() {
        System.out.println("SimpleBean call callme()");
        dependOneBean.callme();
    }
}
