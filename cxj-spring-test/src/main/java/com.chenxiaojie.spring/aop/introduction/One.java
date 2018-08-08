package com.chenxiaojie.spring.aop.introduction;


/**
 * Created by chenxiaojie on 15/10/25.
 */
public class One implements OneInterface {
    @Override
    public void one(String value) {
        System.out.println("one one" + value);
    }


    @Override
    public void same(String value) throws NoSuchMethodException {
        throw new NoSuchMethodException("One has no same method");
    }
}
