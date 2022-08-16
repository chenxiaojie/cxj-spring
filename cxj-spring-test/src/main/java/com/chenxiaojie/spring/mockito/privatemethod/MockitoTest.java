package com.chenxiaojie.spring.mockito.privatemethod;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:23
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateClass.class)
public class MockitoTest {

    @Test
    public void test1() throws Exception {
        PrivateClass privateClass = PowerMockito.spy(new PrivateClass());
        String parameter = "~~test1~~";
        PowerMockito.when(privateClass, "privateMethod", parameter).thenReturn("PowerMockito大神");
        String result = privateClass.publicMethod(parameter);
        System.out.println(result);
    }

    @Test
    public void test2() throws Exception {
        PrivateClass privateClass = PowerMockito.spy(new PrivateClass());
        String parameter = "~~test1~~";
        PowerMockito.when(privateClass.publicMethod(BDDMockito.anyString())).thenReturn("PowerMockito大神");
        String result = privateClass.publicMethod(parameter);
        System.out.println(result);
    }

    @Test
    public void test3() throws Exception {
        PrivateClass privateClass = PowerMockito.spy(new PrivateClass());
        String parameter = "~~test1~~";
        PowerMockito.when(privateClass, "privateMethod", BDDMockito.anyString()).thenReturn("PowerMockito大神");
        String result = privateClass.publicMethod(parameter + parameter);
        System.out.println(result);
    }

}
