package com.chenxiaojie.spring.mockito.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:23
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@PrepareForTest({PrivateClass.class, DependClass.class})
@PowerMockIgnore({"javax.management.*"})
public class MockitoTest2 {

    @Spy
    @Autowired
    private PrivateClass privateClass;

//    @Before
//    public void before() {
//        privateClass = PowerMockito.spy(privateClass);
//    }

    @Test
    public void test1() throws Exception {
        PowerMockito.when(privateClass, "privateMethod", BDDMockito.anyString()).thenReturn("private method mock");
        System.out.println("mocked");
        String result = privateClass.publicMethod("~~test~~");
        System.out.println(result);
    }

    @Test
    public void test2() throws Exception {
        DependClass dependClass = PowerMockito.mock(DependClass.class);
        PowerMockito.when(dependClass.sayHello(BDDMockito.anyString())).thenReturn("depend say hello mock");
        privateClass.setDependClass(dependClass);

        PowerMockito.when(privateClass, "privateMethod", BDDMockito.anyString()).thenReturn("private method mock");
        System.out.println("mocked");
        String result = privateClass.publicMethod("~~test~~");
        System.out.println(result);
    }
}
