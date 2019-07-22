package com.zhoujian.controller;

import com.zhoujian.dao.UserMapper;
import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.User;
import com.zhoujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller

public class MapperController {
//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private UserService userService;

//    @RequestMapping("/queryUser")
//    @ResponseBody
//    public List<User> queryUser(){
//        List<User> users = userMapper.queryUserList();
//        return users;
//    }

    @RequestMapping("/findUserByUsername")
    @ResponseBody
    public User findUserByUsername(@RequestParam(value = "username")String username){
        return userService.findUserByUsername(username);
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/index_v1",method = RequestMethod.GET)
    public String index_v1(){
        return "index_v1";
    }


    @RequestMapping("/queryJsTree")
    @ResponseBody
    public ResponseVO<List<JstreeVO>> queryJsTree(User user, HttpSession session){
        ResponseVO vo = new ResponseVO<List<JstreeVO>>();
        List<JstreeVO> lists = new ArrayList<>();
        //造一些假数据
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId("1");
        jstreeVO.setText("钢铁");
        jstreeVO.setParent("#");
        lists.add(jstreeVO);

        JstreeVO jstreeVO2 = new JstreeVO();
        jstreeVO2.setId("2");
        jstreeVO2.setText("石油");
        jstreeVO2.setParent("#");
        lists.add(jstreeVO2);

        JstreeVO jstreeVO3 = new JstreeVO();
        jstreeVO3.setId("3");
        jstreeVO3.setText("金子");
        jstreeVO3.setParent("1");
        lists.add(jstreeVO3);

        JstreeVO jstreeVO5 = new JstreeVO();
        jstreeVO5.setId("5");
        jstreeVO5.setText("汽油");
        jstreeVO5.setParent("2");
        lists.add(jstreeVO5);

        vo.setData(lists);
        //System.out.println(lists);
        //System.out.println(vo.toString());
        return vo;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


}
