package com.chenxiaojie.spring.feedback.service;

import com.chenxiaojie.spring.feedback.vo.QuestionVO;

/**
 * Created by chenxiaojie on 15/10/25.
 */
public interface QuestionService {
    Boolean addQuestion(QuestionVO questionVO, int operator);


    Boolean updateQuestion(QuestionVO questionVO, int operator);
}
