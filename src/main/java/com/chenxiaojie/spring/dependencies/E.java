package com.chenxiaojie.spring.dependencies;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

/**
 * Created by chenxiaojie on 2016/11/27.
 */
@Repository
public class E implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            System.out.println("spring初始化完毕");
        }
    }
}
