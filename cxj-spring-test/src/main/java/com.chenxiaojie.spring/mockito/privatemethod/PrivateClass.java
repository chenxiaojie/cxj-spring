package com.chenxiaojie.spring.mockito.privatemethod;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:24
 */
public class PrivateClass {

    public String publicMethod(String hello) {
        String result = privateMethod(hello);
        result = "我是公开的陈孝杰, hello : " + result;
        System.out.println(result);
        return result;
    }

    private String privateMethod(String hello) {
        String result = "我是私有的陈孝杰, hello : " + hello;
        System.out.println(result);
        return result;
    }
}
