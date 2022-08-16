package com.chenxiaojie.spring.aop.introduction;

import org.springframework.stereotype.Repository;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Repository
public class Two implements TwoInterface {
    @Override
    public void two(String value) {
        System.out.println("two two" + value);
    }


    @Override
    public void same(String value) {
        System.out.println("two same" + value);
    }
}
