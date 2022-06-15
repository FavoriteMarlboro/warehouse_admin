package com.gx.warehouse_admin.security.filer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//OncePerRequestFilter 每一次只能请求一次过滤器
@Component
@Slf4j
public class LoginFiler extends OncePerRequestFilter {


    //验证码校验
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //设置响应内容类型
        response.setContentType("application/json;charset=utf-8");
        //判断请求是否是post类型的请求，并且判断请求的URL是否以"/login"结尾的
        //equalsIgnoreCase() 进行比较的同时忽略大小写
        //&& 确保是从登录页面传递过来的
        if ((request.getMethod().equalsIgnoreCase("post") && request.getRequestURI().endsWith("/login"))){
            //页面传递的验证码
            String identityCode = request.getParameter("identityCode");
            //session存储的验证码（在验证码生成时便存进了session）
            String identityKey = (String) request.getSession().getAttribute("session_identity");
            //判断生成是否为空
            if (identityCode == null || identityCode.trim().length() == 0){
                PrintWriter out = response.getWriter(); //输出流
                out.write("{\"status\":\"fail\",\"msg\":\"请输入验证码\"}");
                out.flush(); //刷新流
                out.close();
                return; //结束方法，不再调用过滤链
            }
            //校验两次验证码
            if (!identityCode.equalsIgnoreCase(identityKey)){
                PrintWriter out = response.getWriter(); //输出流
                out.write("{\"status\":\"error\",\"msg\":\"验证码不正确\"}");
                out.flush(); //刷新流
                out.close();
                return; //结束方法，不再调用过滤链
            }
        }
        //继续往下执行过滤器链
        filterChain.doFilter(request,response);
    }
}
