package com.zhoujian.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhoujian.domain.Menu;
import com.zhoujian.domain.User;
import com.zhoujian.domain.UserVo;
import com.zhoujian.service.MenuService;
import com.zhoujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
            session.setAttribute("uid",userService.findUserByUsername(username).getUid());
            session.setAttribute("username",username);
            session.setAttribute("utid",userService.uTidByUsername(username));
            String uaccess=userService.uAccessByUsername(username);
            session.setAttribute("menuHtml",uaccessToMenuHtml(uaccess));
            //1 表示管理员
            //2 表示有审核功能人员
            //3 表示普通人员
            return "redirect:/index";
        }
        model.addAttribute("message","用户名或密码错误");
        return "/login";

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

        String usertype="";
        for (User user : userList) {
            switch (user.getUtid()){
                case 1:
                    /*管理员*/
                    usertype="管理员";
                    break;
                case 2:
                    /*审核员*/
                    usertype="审核员";
                    break;
                case 3:
                    /*普通用户*/
                    usertype="普通用户";
                    break;
            }
            userVoList.add(new UserVo(user.getUid(),user.getUsername(),usertype,user.getUstate()==1?"正常":"<span style=\"color:red\">停用</span>","<button onclick=\"updateUser('"+user.getUsername()+"')\" data-toggle=\"modal\" data-target=\"#updateUserModal\" style=\"border:1px solid black;color:black\">修改</button>&nbsp;<button onclick=\"delUser("+user.getUid()+")\" style=\"border:1px solid red;color:red\">删除</button>"));
        }


        return userVoList;
    }

    /**
     * 根据具体用户名查询用户
     * @param username
     * @return
     */
    @RequestMapping("/getUserByUsername")
    @ResponseBody
    public User getUserByUsername(String username){
        return userService.findUserByUsername(username);
    }

    /**
     * 根据部分用户名模糊查询用户
     * @param username
     * @return
     */
    @RequestMapping("/getUserByLikeUsername")
    @ResponseBody
    public List<UserVo> getUserByLikeUsername(String username){
        List<User> userList=userService.findUserByLikeUsername(username);
        List<UserVo> userVoList = new ArrayList<>();

        String usertype="";
        for (User user : userList) {
            switch (user.getUtid()){
                case 1:
                    /*管理员*/
                    usertype="管理员";
                    break;
                case 2:
                    /*审核员*/
                    usertype="审核员";
                    break;
                case 3:
                    /*普通用户*/
                    usertype="普通用户";
                    break;
            }
            userVoList.add(new UserVo(user.getUid(),user.getUsername(),usertype,user.getUstate()==1?"正常":"<span style=\"color:red\">停用</span>","<button onclick=\"updateUser('"+user.getUsername()+"')\" data-toggle=\"modal\" data-target=\"#updateUserModal\" style=\"border:1px solid black;color:black\">修改</button>&nbsp;<button onclick=\"delUser("+user.getUid()+")\" style=\"border:1px solid red;color:red\">删除</button>"));
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

    @RequestMapping("/updateUser")
    @ResponseBody
    public Boolean updateUser(Integer uid, String username, Integer usertype, String upwd) {
        User user=userService.findUserByUid(uid);
        user.setUsername(username);
        user.setUtid(usertype);
        user.setUpwd(upwd);
        switch (usertype){
            case 1:user.setUaccess("1&2&5&6&7&8&9&3&4&10&11&12&13&14&15&16&17");break;
            case 2:user.setUaccess("1&2&5&6&7&8&9&3&4&10&11&12&13&14");break;
            case 3:user.setUaccess("1&2&5&6&8&9&3&4&10&11&13&14");break;
        }
        Boolean res = userService.updateUser(user);
        return res;
    }

    @RequestMapping("/delUserByUid")
    @ResponseBody
    public Boolean delUserByUid(Integer uid){
        Boolean res=userService.delUserByUid(uid);
        return res;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public String insertUser(String username,Integer usertype, String upwd){
        try {
            User user=userService.findUserByUsername(username);
            if (user!=null){
                return "该用户名已存在，请更改用户名";
            }
        }catch (Exception ex){
            return "该用户名已存在，请更改用户名";
        }

        String uaccess = "1&2&5&6&8&9&3&4&10&11&13&14";
        switch (usertype){
            case 1:uaccess="1&2&5&6&7&8&9&3&4&10&11&12&13&14&15&16&17";break;
            case 2:uaccess="1&2&5&6&7&8&9&3&4&10&11&12&13&14";break;
            case 3:uaccess="1&2&5&6&8&9&3&4&10&11&13&14";break;
        }
        Boolean res=userService.insertUser(new User(1,username,upwd,usertype,uaccess,1));
        return res.toString();
    }

    @RequestMapping("/user_add")
    public String to_user_add(){
        return "user_add";
    }
}
