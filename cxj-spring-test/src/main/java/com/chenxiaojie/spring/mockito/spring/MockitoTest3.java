package com.chenxiaojie.spring.mockito.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
public class MockitoTest3 {

    /**
     * spy 可以改变方法内部间接调用与依赖类的的方法调用的返回值, 直接调用该类想改变返回值只能使用mock, 两者不能共存
     */
    @Spy
    @InjectMocks
    @Autowired
    private PrivateClass privateClass;

    @InjectMocks
    @Autowired
    private PrivateClass privateClass2;

    @Mock
    @Autowired
    private DependClass dependClass;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws Exception {
//        PowerMockito.when(dependClass.sayHello(BDDMockito.anyString())).thenReturn("depend say hello mock");
        PowerMockito.when(dependClass, "sayHello", BDDMockito.anyString()).thenReturn("depend say hello mock");
        PowerMockito.when(privateClass, "privateMethod", BDDMockito.anyString()).thenReturn("private method mock");
        System.out.println("start mocked");

        //该mock无效, 说明一个方法不可以被spy 又 被mock.
        //PowerMockito.when(privateClass.publicMethod(BDDMockito.anyString())).thenReturn("public method mock");
        //PowerMockito.when(privateClass, "publicMethod", BDDMockito.anyString()).thenReturn("public method mock");

        System.out.println("start mocked2");
        String result = privateClass.publicMethod("~~test~~");
        System.out.println("result2 : " + result);

        System.out.println("start mocked3");
        PowerMockito.when(privateClass2, "publicMethod", BDDMockito.anyString()).thenReturn("public method mock");

        System.out.println("start mocked4");
        result = privateClass2.publicMethod("~~test~~");
        System.out.println("final result : " + result);
    }
}
