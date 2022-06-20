package com.gx.warehouse_admin.controller;

import com.gx.warehouse_admin.util.ValidateImage.MathPngCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        System.out.println("输出");
        return "login";
    }

    @GetMapping("/indexwms")
    public String indexwms(){
        return "/indexwms";
    }


    /*
        生成验证码图片
        图片如何返回到前端？谁请求该方法就响应（response）给谁
     */
    @RequestMapping("/identity")
    public void identity(HttpSession session, HttpServletResponse response){

                //设置输出的数据类型
        response.setContentType("image/png");
        //创建验证码工具类的实例，并定义验证码图片的大小
        MathPngCaptcha captcha = new MathPngCaptcha(94,42);
        //获取字节输出流
        try {
            OutputStream out = response.getOutputStream();
            //把验证码通过流输出并把验证码保存到session中
            String identityKey = captcha.out(out);
            //把验证码保存到session中
            session.setAttribute("session_identity",identityKey);
            out.flush(); //强制刷新输出流
            out.close(); //关闭流
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("输出流异常，验证码生成失败");
        }
    }


}
