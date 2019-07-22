package com.zhoujian.service.impl;

import com.zhoujian.dao.MenuMapper;
import com.zhoujian.domain.Menu;
import com.zhoujian.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenuList(){
        return menuMapper.queryMenuList();
    }
}
