package com.chenxiaojie.spring.feedback.service;


import com.chenxiaojie.spring.feedback.annotation.AccessInterceptor;
import com.chenxiaojie.spring.feedback.vo.QuestionVO;
import org.springframework.stereotype.Service;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Override
    @AccessInterceptor(contextParamIndex = 0, operatorParamIndex = 1)
    public Boolean addQuestion(QuestionVO questionVO, int operator) {
        System.out.println("我正在添加问题,questionVO:" + questionVO);
        return true;
    }

    @Override
    @AccessInterceptor(contextParamIndex = 0, operatorParamIndex = 1)
    public Boolean updateQuestion(QuestionVO questionVO, int operator) {
        System.out.println("我正在更新问题,questionVO:" + questionVO);
        return true;
    }
}
