package com.shisheng.entity;

import java.util.Date;
import java.util.List;

public class Comment {
    private String id;

    private Date commentTime;

    private String userName;

    private String userId;

    private String commodityId;

    private String answerId;

    private int supportNum;

    private String content;

    private Comment answer;

    public Comment getAnswer() {
        return answer;
    }

    public void setAnswer(Comment answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId == null ? null : answerId.trim();
    }

    public int getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentTime=" + commentTime +
                ", userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", supportNum='" + supportNum + '\'' +
                ", content='" + content + '\'' +
                ", answer=" + answer +
                '}';
    }
}