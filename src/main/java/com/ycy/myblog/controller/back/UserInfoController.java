package com.ycy.myblog.controller.back;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ycy.myblog.bean.UserInfo;
import com.ycy.myblog.service.UserInfoService;
import com.ycy.myblog.utils.PageBean;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/back/user/")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据分页查询用户信息
     */
    @RequestMapping("list")
    public String list(UserInfo user,Model model,Integer page) {
        PageBean<UserInfo> pageBean = userInfoService.getList(user, page);
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("userinfo", user);
        return "back/userinfo/userinfo_list";
    }

    @RequestMapping("clock")
    public String clock() {
        return "back/clock";
    }

    //添加页面跳转;
    @RequestMapping("loadAdd")
    public String loadAdd() {
        return "back/userinfo/userinfo_add";
    }

    //添加;
    @RequestMapping("add")
    public String add(UserInfo userInfo , Model model) {
        boolean mark = userInfoService.add(userInfo);
        if (mark) {
            model.addAttribute("message","添加成功");
        }
        else {
            model.addAttribute("message","添加失败！");
        }
        return "back/userinfo/userinfo_add";
    }

    //修改;
    @RequestMapping("loadUpdate")
    public String loadUpdate(UserInfo userInfo , Model model) {
        UserInfo user = userInfoService.getUser(userInfo);
        model.addAttribute("user",user);
        return "back/userinfo/userinfo_update";
    }

    //修改;
    @RequestMapping("update")
    public String update(UserInfo userInfo , Model model) {
        boolean mark = userInfoService.update(userInfo);
        if (mark) {
            model.addAttribute("message","修改成功");
        }
        else {
            model.addAttribute("message","修改失败！");
        }
        model.addAttribute("user",userInfo);
        return "back/userinfo/userinfo_update";
    }

    //删除用户;
    @RequestMapping("delete")
    public String delete(UserInfo userInfo) {
        userInfoService.delete(userInfo);
        return "redirect:/back/userinfo/list?page=1";
    }
}
