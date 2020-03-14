package com.ycy.myblog.service;

import com.ycy.myblog.bean.UserInfo;
import com.ycy.myblog.utils.PageBean;
import org.apache.catalina.User;

import java.util.List;

/**
 * 业务调用接口
 */
public interface UserInfoService {
    //分页查询;
    PageBean<UserInfo> getList(UserInfo userInfo, Integer page);
    //查取用户;
    UserInfo getUser(UserInfo userInfo);
    //添加用户;
    boolean add(UserInfo userInfo);
    //更新用户;
    boolean update(UserInfo userInfo);
    //删除用户（逻辑）;
    boolean delete(UserInfo userInfo);
    //用户登录;
    UserInfo isLogin(UserInfo userInfo);
    //查询用户个数;
    int getCount();
}
