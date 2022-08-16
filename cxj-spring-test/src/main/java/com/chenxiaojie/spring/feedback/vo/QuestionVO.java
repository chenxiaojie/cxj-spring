package com.chenxiaojie.spring.feedback.vo;

public class QuestionVO {

    private int questionId;
    private int replyId;
    private String content;

    public QuestionVO() {
    }

    public QuestionVO(int replyId, String content) {
        this.replyId = replyId;
        this.content = content;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "replyId=" + replyId +
                ", content='" + content + '\'' +
                '}';
    }
}
