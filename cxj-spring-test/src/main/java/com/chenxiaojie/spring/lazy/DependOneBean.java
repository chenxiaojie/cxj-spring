package com.chenxiaojie.spring.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2020-07-29
 * Time: 16:23
 */
@Component
public class DependOneBean {

    @Lazy
    @Autowired
    private SimpleBean simpleBean;

    public void depend() {
        simpleBean.callme();
    }

    public void callme() {
        System.out.println("DependOneBean call callme()");
    }

    @Async
    public void callme2() {
        System.out.println("DependOneBean call callme2()");
    }
}
