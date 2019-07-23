package com.zhoujian.service;

import com.zhoujian.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     *查询所有菜单项
     */
    public List<Menu> queryMenuList();

    /***
     * 根据用户的权限获取菜单列表
     * @param access
     * @return
     */
    public List<Menu> queryMenuListByAccess(String access);
}
