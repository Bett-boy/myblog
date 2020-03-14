package com.ycy.myblog.controller.back;

import com.ycy.myblog.bean.ArticleInfo;
import com.ycy.myblog.bean.MessageInfo;
import com.ycy.myblog.bean.UserInfo;
import com.ycy.myblog.service.ArticleService;
import com.ycy.myblog.service.MessageInfoService;
import com.ycy.myblog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/back/")
public class BackIndexController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageInfoService messageInfoService;
    //登陆页面加载;
    @RequestMapping(value = "login")
    public String login(HttpSession session) {
        //退出时删除session；
        session.invalidate();
        return "back/login";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "back/index";
    }

    @RequestMapping(value = "main")
    public String main(Model model, HttpServletRequest request) {
        int count = userInfoService.getCount();
        ArticleInfo articleinfo = new ArticleInfo();
        Long articleCount = articleService.getArticleCount(articleinfo);
        MessageInfo messageInfo = new MessageInfo();
        long noMessages = messageInfoService.getNoMessages(messageInfo);
        //获取本机ip；
        String remoteAddr = request.getRemoteAddr();
        model.addAttribute("ip",remoteAddr);
        model.addAttribute("userCount",count);
        model.addAttribute("articleCount",articleCount);
        model.addAttribute("noMessages",noMessages);
        return "back/main";
    }

    //用户登录;
    @RequestMapping(value = "userLogin")
    public String userLogin(UserInfo userInfo, Model model, HttpSession session,HttpServletRequest request) {
            UserInfo login = userInfoService.isLogin(userInfo);
        if (login != null) {
            //获取本机ip；
            String remoteAddr = request.getRemoteAddr();
            model.addAttribute("ip",remoteAddr);
            //获取当前登录时间;
            Date date = new Date();
            //时间格式化；
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH时mm分ss秒");
            String s = format.format(date);
            session.setAttribute("loginTime",s);
            session.setAttribute("userinfo",login);
            return "back/index";
        }
        else {
            model.addAttribute("message","用户名或者密码错误！");
            return "back/login";
        }
    }
}
