package com.ycy.myblog.dao;

import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户数据访问接口
 */

@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Integer userId);


    int insert(UserInfo record);


    int insertSelective(UserInfo record);


    UserInfo selectByPrimaryKey(Integer userId);


    int updateByPrimaryKeySelective(UserInfo record);


    int updateByPrimaryKey(UserInfo record);


    List<UserInfo> getUserList(UserInfo userInfo);


    Long getUserCount(UserInfo userInfo);


    UserInfo isLogin(UserInfo userInfo);


}