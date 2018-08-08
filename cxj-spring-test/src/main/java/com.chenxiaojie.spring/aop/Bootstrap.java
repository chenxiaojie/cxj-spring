package com.chenxiaojie.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/appcontext-aop.xml"})
public class Bootstrap {

    @Autowired
    @Qualifier("someone")
    private Int anInt;

    @Test
    public void test() {
        anInt.say("陈孝杰");
    }
}
