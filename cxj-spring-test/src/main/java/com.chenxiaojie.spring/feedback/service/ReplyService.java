package com.chenxiaojie.spring.feedback.service;

import com.chenxiaojie.spring.feedback.vo.ReplyVO;

/**
 * Created by chenxiaojie on 15/10/25.
 */
public interface ReplyService {
    Boolean addReply(ReplyVO replyVO, int operator);
}
