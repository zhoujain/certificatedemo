package com.zhoujian.controller;

import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;
import com.zhoujian.domain.User;
import com.zhoujian.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller

public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/loginUser")
    public String loginUser (HttpServletResponse response, @RequestParam(value = "username") String username, @RequestParam(value = "upwd") String upwd, HttpSession session)throws Exception{
        Boolean islogin = userService.loginUser(username,upwd);
        if(islogin){
            session.setAttribute("username",username);
            session.setAttribute("utid",userService.uTidByUsername(username));
            session.setAttribute("uaccess",userService.uAccessByUsername(username));
            //1 表示管理员
            //2 表示有审核功能人员
            //3 表示普通人员
            return "index";
        }
        return "login";

    }
}
