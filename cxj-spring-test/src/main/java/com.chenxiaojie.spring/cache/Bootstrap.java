package com.chenxiaojie.spring.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/appcontext-cache.xml"})
public class Bootstrap {

    @Autowired
    private IInt iInt;

    @Test
    public void test() {
        for (int i = 0; i < 2; i++) {
            System.out.println(iInt.get(i));
        }

        for (int i = 0; i < 2; i++) {
            System.out.println(iInt.get(i));
        }

        System.out.println(iInt.get2(100));
        System.out.println(iInt.get2(100));
    }

    @Test
    public void test2() {
        System.out.println(iInt.get(1));
        System.out.println(iInt.get(1));
        System.out.println(iInt.put(1, 1000));
        System.out.println(iInt.get(1));
    }

    @Test
    public void test3() {
        System.out.println(iInt.get(1));
        System.out.println(iInt.get(1));
        System.out.println(iInt.evict(1));
        System.out.println(iInt.get(1));
        System.out.println(iInt.get(1));
    }
}
