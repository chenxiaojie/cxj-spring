package com.chenxiaojie.spring.feedback.checker;

import com.chenxiaojie.spring.feedback.service.QuestionService;
import com.chenxiaojie.spring.feedback.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Component
public class ReplyServiceInvokeChecker extends AbstractCanInvokeChecker<ReplyVO> {

    @Autowired
    private QuestionService questionService;

    public boolean addReply(Integer operator, ReplyVO questionVO, Object[] args) {
        System.out.println("addReply我正在check, QuestionVO:" + questionVO);
        return true;
    }

    @Override
    protected ReplyVO toContextObject(Object context) {
        if (context instanceof ReplyVO) {
            return ((ReplyVO) context);
        }
        throw new IllegalArgumentException("unknown context : " + context.getClass().getName());
    }
}
