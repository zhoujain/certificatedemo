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

    @Autowired
    private MapperController mapperController;

    @Test
    public void test(){
        List<User> users = userMapper.queryUserList();
        System.out.println(users);

    }
    @Test
    public void test1(){
        User user = mapperController.findUserByUsername("zhoujian");
        System.out.println(user);
    }
}
