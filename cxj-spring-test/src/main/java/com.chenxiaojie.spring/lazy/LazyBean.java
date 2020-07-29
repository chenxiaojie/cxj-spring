package com.chenxiaojie.spring.lazy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LazyBean {

    @Autowired
    private DependOneBean dependOneBean;

    @Autowired
    private SimpleBean simpleBean;

    @Lazy
    @Autowired
    private LazyBean lazyBean;

    public void lazy() {
        simpleBean.simple();
        dependOneBean.depend();
    }

    /**
     * 自己依赖自己的情况下,无法走代理,会抛npe
     */
    public void lazy2() {
        //成员变量是null
        //lazyBean.simpleBean.simple();
        //lazyBean.dependOneBean.depend();

        //私有方法不可以走代理，访问成员变量会抛npe
        //lazyBean.lazy3();

        //公有方法可以走代理不会抛npe
        lazyBean.lazy4();
    }

    private void lazy3() {
        lazyBean.lazy();
    }

    public void lazy4() {
        lazyBean.lazy();
    }
}
