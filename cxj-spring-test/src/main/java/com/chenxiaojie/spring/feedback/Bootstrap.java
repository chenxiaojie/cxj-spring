package com.chenxiaojie.spring.feedback;

import com.chenxiaojie.spring.feedback.service.QuestionService;
import com.chenxiaojie.spring.feedback.service.ReplyService;
import com.chenxiaojie.spring.feedback.vo.QuestionVO;
import com.chenxiaojie.spring.feedback.vo.ReplyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenxiaojie on 15/8/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/appcontext-feedback.xml"})
public class Bootstrap {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private QuestionService questionService;

    @Test
    public void test() {
        QuestionVO questionVO = new QuestionVO(1, "我是问题内容");
        questionService.addQuestion(questionVO, 123);
        questionService.updateQuestion(questionVO, 123);
    }


    @Test
    public void test1() {
        ReplyVO replyVO = new ReplyVO(1, "我是问题内容");
        replyService.addReply(replyVO, 123);
    }
}
