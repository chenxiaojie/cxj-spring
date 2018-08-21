package com.chenxiaojie.spring.feedback.checker;

import com.chenxiaojie.spring.feedback.service.QuestionService;
import com.chenxiaojie.spring.feedback.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Component
public class QuestionServiceInvokeChecker extends AbstractCanInvokeChecker<QuestionVO> {

    @Autowired
    private QuestionService questionService;

    public boolean addQuestion(Integer operator, QuestionVO questionVO, Object[] args) {
        System.out.println("addQuestion我正在check, QuestionVO:" + questionVO);
        return true;
    }

    public boolean updateQuestion(Integer operator, QuestionVO questionVO, Object[] args) {
        System.out.println("updateQuestion我正在check, QuestionVO:" + questionVO);
        return true;
    }

    @Override
    protected QuestionVO toContextObject(Object context) {
        if (context instanceof QuestionVO) {
            return ((QuestionVO) context);
        }
        throw new IllegalArgumentException("unknown context : " + context.getClass().getName());
    }
}
