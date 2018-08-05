package com.chenxiaojie.spring.aop.introduction;

import org.springframework.stereotype.Repository;

/**
 * Created by chenxiaojie on 15/10/25.
 */
//@Repository
public class One implements OneInterface {
    @Override
    public void one(String value) {
        System.out.println("one one" + value);
    }

    @Override
    public void same(String value) {
        System.out.println("one same" + value);
    }
}
