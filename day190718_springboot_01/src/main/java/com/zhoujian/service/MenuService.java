package com.zhoujian.service;

import com.zhoujian.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     *查询所有菜单项
     */
    public List<Menu> queryMenuList();
}
