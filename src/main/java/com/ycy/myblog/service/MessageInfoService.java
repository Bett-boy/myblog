package com.ycy.myblog.service;

import com.ycy.myblog.bean.MessageInfo;

import java.util.List;

public interface MessageInfoService {
    //查询所有留言信息；
    List<MessageInfo> getMessageList(MessageInfo messageInfo);
    //留言的显示、不显示修改;
    boolean update(MessageInfo messageInfo);
    //删除留言;
    boolean delete(Integer messageId);
    //查询有效留言信息;
    List<MessageInfo> getMessages();
    //提交留言;
    boolean add(MessageInfo messageInfo);
    //待审核留言；
    //待审核留言；
    Long getNoMessages(MessageInfo messageInfo);
}
