package com.chenxiaojie.spring.feedback.checker;

import com.chenxiaojie.spring.feedback.service.QuestionService;
import com.chenxiaojie.spring.feedback.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Component
public class QuestionServiceInvokeChecker extends AbstractCanInvokeChecker<QuestionVO> {

    @Autowired
    private QuestionService questionService;

    @Bean
    public Checker<QuestionVO> addQuestinChecker() {
        return new Checker<QuestionVO>() {
            @Override
            public boolean check(Integer operator, QuestionVO dbObject, QuestionVO updateObject, Object[] args) {
                System.out.println("addQuestion我正在check, QuestionVO:" + dbObject);
                questionIsSolevd();
                return true;
            }
        };
    }

    public boolean questionIsSolevd() {
        return true;
    }

    @Bean
    public Checker<QuestionVO> addQuestinChecker2() {
        return new Checker<QuestionVO>() {
            @Override
            public boolean check(Integer operator, QuestionVO dbObject, QuestionVO updateObject, Object[] args) {
                System.out.println("addQuestion我正在check, QuestionVO:" + dbObject);
                if (questionIsSolevd()) {

                }
                return true;
            }
        };
    }


    @Override
    protected QuestionVO toContextObject(Object context) {
        Integer questionId = null;
        if (context instanceof QuestionVO) {
            questionId = ((QuestionVO) context).getQuestionId();
        } else if (context instanceof Integer) {
            questionId = ((Integer) context);
        } else {
            throw new IllegalArgumentException("unknown context : " + context.getClass().getName());
        }
        return new QuestionVO();
    }
}