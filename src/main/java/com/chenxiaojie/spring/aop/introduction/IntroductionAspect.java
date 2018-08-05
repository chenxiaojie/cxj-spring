package com.chenxiaojie.spring.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@Aspect
@Component
public class IntroductionAspect {

    @DeclareParents(value = "com.chenxiaojie.spring.aop.introduction.Two",
            defaultImpl = One.class)
    public OneInterface oneInterface;

}
