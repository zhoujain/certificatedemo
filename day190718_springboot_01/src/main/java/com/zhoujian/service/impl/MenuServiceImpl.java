package com.zhoujian.service.impl;

import com.zhoujian.dao.MenuMapper;
import com.zhoujian.domain.Menu;
import com.zhoujian.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenuList(){
        return menuMapper.queryMenuList();
    }

    @Override
    public List<Menu> queryMenuListByAccess(String access) {
        String[] accesses=access.split("&");

        List<Menu> menuList=menuMapper.queryMenuList();
        List<Menu> myMenuList=new ArrayList<>();
        for (Menu menu : menuList) {
            for (String s : accesses) {
                if (menu.getMid().toString().equals(s)){
                    myMenuList.add(menu);
                    break;
                }
            }
        }

        return myMenuList;
    }
}
