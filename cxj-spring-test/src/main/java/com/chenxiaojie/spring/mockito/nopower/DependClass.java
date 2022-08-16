package com.chenxiaojie.spring.mockito.nopower;

import org.springframework.stereotype.Service;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:24
 */
@Service
public class DependClass {

    public String sayHello(String hello) {
        String result = "DependClass.sayHello : " + hello;
        System.out.println("call: " + result);
        return result;
    }

    public String sayHello2(String hello) {
        String result = "DependClass.sayHello2 : " + hello;
        System.out.println("call: " + result);
        return result;
    }
}
