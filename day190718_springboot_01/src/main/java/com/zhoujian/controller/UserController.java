package com.zhoujian.controller;

import com.sun.deploy.net.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;
import com.zhoujian.domain.CertificateVo;
import com.zhoujian.domain.Menu;
import com.zhoujian.domain.User;
import com.zhoujian.domain.UserVo;
import com.zhoujian.service.MenuService;
import com.zhoujian.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
            session.setAttribute("menuHtml",uaccessToMenuHtml(uaccess));
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
        if (session.getAttribute("menuHtml")!=null) {
            session.removeAttribute("menuHtml");
        }
        return "login";
    }

    /**
     * 跳转用户列表界面
     * @return
     */
    @RequestMapping("/user_list")
    public String to_user_list(){
        return "user_list";
    }

    /**
     * 向前端返回用户列表JSON数据
     * @return
     */
    @RequestMapping("/getUsersDataJSON")
    @ResponseBody
    public List<UserVo> getUsersDataJSON() {
        List<User> userList=userService.queryAllUser();
        List<UserVo> userVoList = new ArrayList<>();

        for (User user : userList) {
            switch (user.getUtid()){
                case 1:
                    /*管理员*/
                    userVoList.add(new UserVo(user.getUid(),user.getUsername(),"管理员","<a>修改</a>&nbsp;&nbsp;<a>删除</a>"));
                    break;
                case 2:
                    /*审核员*/
                    userVoList.add(new UserVo(user.getUid(),user.getUsername(),"审核员","<a>修改</a>&nbsp;&nbsp;<a>删除</a>"));
                    break;
                case 3:
                    /*普通用户*/
                    userVoList.add(new UserVo(user.getUid(),user.getUsername(),"普通用户","<a>修改</a>&nbsp;&nbsp;<a>删除</a>"));
                    break;
            }
        }

        return userVoList;
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
