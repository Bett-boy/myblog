package com.ycy.myblog.bean;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
public class MessageInfo implements Serializable {

    private Integer messageId;


    private String messageContent;


    private Date messageTime;


    private String messageName;


    private String messageMark;


    private static final long serialVersionUID = 1L;


    public Integer getMessageId() {
        return messageId;
    }


    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }


    public String getMessageContent() {
        return messageContent;
    }


    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }


    public Date getMessageTime() {
        return messageTime;
    }


    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }


    public String getMessageName() {
        return messageName;
    }


    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }


    public String getMessageMark() {
        return messageMark;
    }


    public void setMessageMark(String messageMark) {
        this.messageMark = messageMark;
    }
}