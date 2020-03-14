package com.ycy.myblog.dao;

import com.ycy.myblog.bean.MessageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 留言管理数据接口
 */
@Mapper
public interface MessageInfoMapper {

    int deleteByPrimaryKey(Integer messageId);


    int insert(MessageInfo record);


    int insertSelective(MessageInfo record);


    MessageInfo selectByPrimaryKey(Integer messageId);


    int updateByPrimaryKeySelective(MessageInfo record);


    int updateByPrimaryKey(MessageInfo record);

    //查询所有留言信息;
    List<MessageInfo> getMessageList(MessageInfo messageInfo);

    //查询有效留言信息;
    List<MessageInfo> getMessages();

    //待审核留言；
    Long getNoMessages(MessageInfo messageInfo);
}