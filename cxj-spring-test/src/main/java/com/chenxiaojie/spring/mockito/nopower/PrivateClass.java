package com.chenxiaojie.spring.mockito.nopower;

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
        String result = "##self##publicMethod value: " + hello;
        System.out.println(result);

        System.out.println("##depend##sayHello return value: " + dependClass.sayHello(hello));

        result = privateMethod(hello);
        System.out.println("##private##privateMethod return value: " + result);
        return result;
    }

    public String privateMethod(String hello) {
        String result = "PrivateClass.privateMethod : " + hello;
        System.out.println("call: " + result);
        return result;
    }
}
