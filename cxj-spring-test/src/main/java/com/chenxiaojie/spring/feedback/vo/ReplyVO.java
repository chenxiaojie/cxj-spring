package com.chenxiaojie.spring.feedback.vo;

public class ReplyVO {

    private int replyId;
    private int questionId;
    private String content;

    public ReplyVO() {
    }

    public ReplyVO(int replyId, String content) {
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

    @Override
    public String toString() {
        return "ReplyVO{" +
                "replyId=" + replyId +
                ", content='" + content + '\'' +
                '}';
    }
}
