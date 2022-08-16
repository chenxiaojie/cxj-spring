package com.chenxiaojie.spring.feedback.checker;

import com.google.common.collect.ArrayListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Component
public class CanInvokeCheckerHelper {

    @Autowired
    private List<AbstractCanInvokeChecker> checkers;

    private ArrayListMultimap<Class, CanInvokeChecker> multiMap = ArrayListMultimap.create();

    @PostConstruct
    public void init() {
        System.out.println(multiMap);
    }
}
