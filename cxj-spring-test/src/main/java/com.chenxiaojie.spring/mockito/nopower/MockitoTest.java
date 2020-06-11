package com.chenxiaojie.spring.mockito.nopower;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:23
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Mock
    @Autowired
    private DependClass dependClass;

    @Spy
    @InjectMocks
    @Autowired
    private PrivateClass privateClass;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws Exception {
        Mockito.when(dependClass.sayHello(BDDMockito.anyString())).thenReturn("depend say hello mock test1");
        Mockito.when(privateClass.privateMethod(BDDMockito.anyString())).thenReturn("private method mock test1");

        System.out.println("start mocked");
        String result = privateClass.publicMethod("~~test~~");
        System.out.println("final result : " + result);

        dependClass.sayHello2("sayHello2 p");
    }

}
