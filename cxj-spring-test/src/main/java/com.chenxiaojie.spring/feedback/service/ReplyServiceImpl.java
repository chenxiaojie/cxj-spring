package com.chenxiaojie.spring.feedback.service;


import com.chenxiaojie.spring.feedback.annotation.AccessInterceptor;
import com.chenxiaojie.spring.feedback.vo.ReplyVO;
import org.springframework.stereotype.Service;

/**
 * Created by chenxiaojie on 15/10/25.
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Override
    @AccessInterceptor(operatorParamIndex = 1, contextParamIndex = 0)
    public Boolean addReply(ReplyVO replyVO, int operator) {
        System.out.println("我正在添加回复,replyVO:" + replyVO);
        return false;
    }

}
