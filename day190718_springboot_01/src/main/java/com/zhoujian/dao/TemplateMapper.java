package com.zhoujian.dao;

import com.zhoujian.domain.Template;

import java.util.List;

public interface TemplateMapper {

    /**
     * 查询所有模板
     */
    public List<Template> queryTemplateList();

    /**
     * 添加模板
     */
    public Integer addTemplate(Template template);

    /**
     * 修改模板
     */
    public Integer editTemplate(Template template);

    /**
     * 删除模板
     */
    public Integer deleteTemplate(Integer tid);
}
