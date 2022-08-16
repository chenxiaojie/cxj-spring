package com.chenxiaojie.spring.namespace.bootstrap;

import com.chenxiaojie.spring.namespace.annotation.Xiaojie;
import org.springframework.stereotype.Repository;


@Repository
public class CustomBean {

    @Xiaojie("custom_bean_test")
    public void test() {

    }
}
