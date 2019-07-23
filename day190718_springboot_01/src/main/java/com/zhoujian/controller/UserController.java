package com.zhoujian.controller;

import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;
import com.zhoujian.domain.Menu;
import com.zhoujian.domain.User;
import com.zhoujian.service.MenuService;
import com.zhoujian.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;


    @RequestMapping("/loginUser")
    public String loginUser (Model model,HttpServletResponse response, @RequestParam(value = "username") String username, @RequestParam(value = "upwd") String upwd, HttpSession session)throws Exception{
        Boolean islogin = userService.loginUser(username,upwd);
        if(islogin){
            session.setAttribute("username",username);
            session.setAttribute("utid",userService.uTidByUsername(username));
            String uaccess=userService.uAccessByUsername(username);
            model.addAttribute("menuHtml",uaccessToMenuHtml(uaccess));
            //1 表示管理员
            //2 表示有审核功能人员
            //3 表示普通人员
            return "index";
        }
        return "login";

    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping("/logoutUser")
    public String logoutUser(HttpSession session){
        if (session.getAttribute("username")!=null){
            session.removeAttribute("username");
        }
        if (session.getAttribute("utid")!=null) {
            session.removeAttribute("utid");
        }
        return "login";
    }

    /**
     * 用户权限 转 菜单的HTML内容
     * @param access
     * @return
     */
    private String uaccessToMenuHtml(String access){

        StringBuilder menuHtml= new StringBuilder();
        List<Menu> myMenuList=menuService.queryMenuListByAccess(access);
        for (Menu menuItem : myMenuList) {
            if (menuItem.getMpid().toString().equals("0")){
                if (menuHtml.toString().equals("")){
                    menuHtml.append("<li><a href=\"#\"><i class=\"fa fa-home\"></i><span class=\"nav-label\">").append(menuItem.getMname()).append("</span><span class=\"fa arrow\"></span></a>");
                }
                else {
                    menuHtml.append("</ul></li><li><a href=\"#\"><i class=\"fa fa-home\"></i><span class=\"nav-label\">").append(menuItem.getMname()).append("</span><span class=\"fa arrow\"></span></a>");
                }
                menuHtml.append("<ul class=\"nav nav-second-level\">");
            }else {
                menuHtml.append("<li><a class=\"J_menuItem\" href=\"").append(menuItem.getMurl()).append("\" >").append(menuItem.getMname()).append("</a></li>");
            }
        }
        menuHtml.append("</ul></li>");
        return menuHtml.toString();
    }
}
