package com.chenxiaojie.spring.mockito.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:24
 */
@Service
public class PrivateClass {

    @Autowired
    private DependClass dependClass;

    public void setDependClass(DependClass dependClass) {
        this.dependClass = dependClass;
    }

    public String publicMethod(String hello) {
        String result = privateMethod(hello);
        result = "我是公开的陈孝杰, hello : " + result;
        System.out.println(result);
        System.out.println("DependClass : " + dependClass.sayHello(hello));
        return result;
    }

    private String privateMethod(String hello) {
        String result = "我是私有的陈孝杰, hello : " + hello;
        System.out.println(result);
        return result;
    }
}
