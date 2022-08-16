package com.chenxiaojie.spring.configuration.thiscallaop;

import org.springframework.context.annotation.Bean;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-08-12
 * Time: 22:02
 */
public class AService {

    @Bean
    public String aop() {
        String result = "我是aop方法，我只会被调用一次";
        System.out.println("aop call");
        return result;
    }

    public String noAop() {
        String result = "我是不是aop方法，我每次都会被调用";
        System.out.println("noAop call");
        return result;
    }
}
