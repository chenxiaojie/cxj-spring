package com.chenxiaojie.spring.aop;

import org.springframework.stereotype.Repository;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@Repository
public class Someone implements Int {
    @Override
    public String say(String name) {
        String result = "啥?" + name;
        System.out.println(result);
//        int i = 1 / 0;
        return result;
    }
}
