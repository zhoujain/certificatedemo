package com.zhoujian.service.impl;

import com.zhoujian.dao.TemplateMapper;
import com.zhoujian.domain.Template;
import com.zhoujian.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<Template> queryTemplateList(){
        return templateMapper.queryTemplateList();
    }
}
