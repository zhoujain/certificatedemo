package com.zhoujian;


import com.zhoujian.controller.MapperController;
import com.zhoujian.dao.UserMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.User;
import com.zhoujian.service.UserService;
import com.zhoujian.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;



    @Test
    public void test(){
        List<User> users = userMapper.queryUserList();
        System.out.println(users);

    }

    @Test
    public void test1(){
        User user = userMapper.findUserByUsername("zhoujian");
        System.out.println(user);
    }

    @Test
    public void test2(){
        User user = userMapper.findUserByUid(1001);
        System.out.println(user);
    }

    @Test
    public void test3(){
        String pwd = userMapper.findUpwdByUsername("zhoujian");
        System.out.println(pwd);
    }

    @Test
    public void test4(){
        User user = new User();
        user.setUsername("lisi");
        user.setUpwd("123456");
        user.setUaccess("1&2&3");
        user.setUstate(1);
        user.setUtid(1);
        Boolean isAdd = userMapper.insertUser(user);
        System.out.println(isAdd);
    }

    @Test
    public void test5(){
        User user = userMapper.findUserByUid(1004);
        user.setUsername("lisi1");
        user.setUpwd("123");
        user.setUtid(2);
        user.setUstate(2);
        user.setUaccess("1&2&3&4");
        Boolean isUpdate = userMapper.updateUser(user);
        System.out.println(isUpdate);
    }

    @Test
    public void test6(){
        Boolean isDelete = userMapper.deleteUser(1004);
        System.out.println(isDelete);
    }




}
