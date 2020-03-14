package com.ycy.myblog.service.impl;

import com.ycy.myblog.bean.UserInfo;
import com.ycy.myblog.dao.UserInfoMapper;
import com.ycy.myblog.service.UserInfoService;
import com.ycy.myblog.utils.Const;
import com.ycy.myblog.utils.PageBean;
import com.ycy.myblog.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    //注入mapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    //分页查询;
    public PageBean<UserInfo> getList(UserInfo userInfo, Integer page) {
        //获取总记录数;
        int allRow = userInfoMapper.getUserCount(userInfo).intValue();
        //获取总页数;
        int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);
        //获取当前是第几页；
        int currentPage = PageUtil.countCurrentPage(page);
        //获取起始记录页数；
        int startPage = PageUtil.startPage(Const.PAGE_SIZE, currentPage);
        //对page进行判断;
        if (page >= 0) {
            userInfo.setStart(startPage);
            userInfo.setLength(Const.PAGE_SIZE);
        }
        else {
            userInfo.setStart(-1);
            userInfo.setLength(-1);
        }

        List<UserInfo> userList = userInfoMapper.getUserList(userInfo);
        PageBean<UserInfo> pageBean = new PageBean<>();
        pageBean.setAllRow(allRow);
        pageBean.setCurrentPage(currentPage);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(userList);
        return pageBean;
    }

    @Override
    //查取用户;
    public UserInfo getUser(UserInfo userInfo) {
        //判断用户是否为空
        if (userInfo != null && userInfo.getUserId() != null) {
            return userInfoMapper.selectByPrimaryKey(userInfo.getUserId());
        }
        return null;
    }

    @Override
    //添加
    public boolean add(UserInfo userInfo) {
        try {
            if (userInfo != null) {
                userInfo.setUserMark(Const.MARK_YEX);
            }
            else {
                userInfo.setUserMark(Const.MARK_NO);
            }
            int i = userInfoMapper.insertSelective(userInfo);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //修改
    public boolean update(UserInfo userInfo) {
        try {
            if (userInfo != null) {
                userInfo.setUserMark(Const.MARK_YEX);
            }
            else {
                userInfo.setUserMark(Const.MARK_NO);
            }
            int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //删除用户
    public boolean delete(UserInfo userInfo) {
        try {
            userInfo.setUserMark(Const.MARK_NO);

            int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);

            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //用户登录;
    public UserInfo isLogin(UserInfo userInfo) {
            try {
                return userInfoMapper.isLogin(userInfo);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
    }

    @Override
    //查取用户个数；
    public int getCount() {
        return userInfoMapper.getUserCount(null).intValue();
    }

}
