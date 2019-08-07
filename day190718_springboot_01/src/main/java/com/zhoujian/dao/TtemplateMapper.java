package com.zhoujian.dao;


import com.zhoujian.domain.Ttemplate;

import java.util.List;

public interface TtemplateMapper {
    /**
     * 查询所有模板
     */
    public List<Ttemplate> queryTtemplateList();

    /**
     * 添加模板
     */
    public Integer addTtemplate(Ttemplate template);

    /**
     * 修改模板
     */
    public Integer editTtemplate(Ttemplate template);

    /**
     * 删除模板
     */
    public Integer deleteTtemplate(Integer ttid);

    /**
     * 根据id查模板
     */
    public Ttemplate getTtemplateById(Integer ttid);
    /**
     * 插入得id
     */
    public int addTtemplateToTtid(Ttemplate template);
}
