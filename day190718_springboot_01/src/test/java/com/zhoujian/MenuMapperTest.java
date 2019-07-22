package com.zhoujian;

import com.zhoujian.dao.MenuMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class MenuMapperTest {
    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void queryMenuList(){
        List<Menu> menus = menuMapper.queryMenuList();
        System.out.println(menus);
    }

    @Test
    public void addMenuItem(){
        Menu menu=new Menu();
        menu.setMname("修改证书");
        menu.setMicon(null);
        menu.setMurl("ssss");
        menu.setMpid(1);
        Integer result = menuMapper.addMenuItem(menu);
        System.out.println(result);
    }

    @Test
    public void editMenuItem(){
        Menu menu=new Menu();
        menu.setMname("修改证书");
        menu.setMicon("米有icon");
        menu.setMurl("ssss");
        menu.setMpid(1);
        menu.setMid(5);
        Integer result = menuMapper.editMenuItem(menu);
        System.out.println(result);
    }

    @Test
    public void deleteMenuItem(){
        Integer mid = 5;
        Integer result = menuMapper.deleteMenuItem(mid);
        System.out.println(result);
    }
}
