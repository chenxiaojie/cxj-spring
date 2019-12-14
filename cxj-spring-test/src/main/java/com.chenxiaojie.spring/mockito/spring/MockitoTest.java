package com.chenxiaojie.spring.mockito.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Desc: Hello World
 * ------------------------------------
 * Author: chenxiaojie
 * Date: 2019-07-11
 * Time: 15:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockitoTest.class)
@ComponentScan(basePackages = "com.chenxiaojie.spring.mockito.spring")
public class MockitoTest {

//    @Spy
    @InjectMocks
    @Autowired
    private PrivateClass privateClass;

    @Mock
    @Autowired
    private DependClass dependClass;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws Exception {
        PowerMockito.when(dependClass, "sayHello", BDDMockito.anyString()).thenReturn("dependClass mock");

        //该mock无效
        PowerMockito.when(privateClass, "publicMethod", BDDMockito.anyString()).thenReturn("privateClass mock");

        System.out.println("mocked");

        String result = privateClass.publicMethod("~~test~~");
        System.out.println("result : " + result);
    }

}
