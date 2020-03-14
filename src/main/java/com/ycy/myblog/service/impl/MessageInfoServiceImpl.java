package com.ycy.myblog.service.impl;

import com.ycy.myblog.bean.MessageInfo;
import com.ycy.myblog.dao.MessageInfoMapper;
import com.ycy.myblog.service.MessageInfoService;
import com.ycy.myblog.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageInfoServiceImpl implements MessageInfoService {

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Override
    public List<MessageInfo> getMessageList(MessageInfo messageInfo) {
        return messageInfoMapper.getMessageList(messageInfo);
    }

    @Override
    public boolean update(MessageInfo messageInfo) {
        try {
            int i = messageInfoMapper.updateByPrimaryKeySelective(messageInfo);
            if (i > 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //删除留言
    public boolean delete(Integer messageId) {
        try {
            int i = messageInfoMapper.deleteByPrimaryKey(messageId);
            if (i > 0) {
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //查询有效留言信息;
    public List<MessageInfo> getMessages() {
        return messageInfoMapper.getMessages();
    }

    @Override
    public boolean add(MessageInfo messageInfo) {
        try {
            if (messageInfo != null) {
                messageInfo.setMessageTime(new Date());
                messageInfo.setMessageMark(Const.MARK_NO);
            }
            int insert = messageInfoMapper.insert(messageInfo);
            if (insert > 0) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Long getNoMessages(MessageInfo messageInfo) {
        return messageInfoMapper.getNoMessages(messageInfo).longValue();
    }
}
