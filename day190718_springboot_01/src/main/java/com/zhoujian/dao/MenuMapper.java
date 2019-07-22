package com.zhoujian.dao;

import com.zhoujian.domain.Menu;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface MenuMapper {

    /**
     * 查询所有菜单项
     * @return
     */
    public List<Menu> queryMenuList();

    /**
     *添加新的菜单项
     */
    public Integer addMenuItem(Menu menu);

    /**
     * 修改已有菜单项
     */
    public Integer editMenuItem(Menu menu);

    /**
     *删除已有菜单项
     */
    public Integer deleteMenuItem(Integer mid);
}
