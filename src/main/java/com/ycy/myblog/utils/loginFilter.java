package com.ycy.myblog.utils;

import com.ycy.myblog.bean.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/back/*")
public class loginFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取请求路径；
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        //从session中获取userinfo信息；
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if (requestURI.contains("/userLogin")) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        //判断是否存在userInfo，如果不存在强制跳转至login页面;
        else if (userInfo != null) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            request.getRequestDispatcher("/back/login").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
