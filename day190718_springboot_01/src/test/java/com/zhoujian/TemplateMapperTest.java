package com.zhoujian;

import com.zhoujian.dao.TemplateMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class TemplateMapperTest {
    @Autowired
    private TemplateMapper templateMapper;

    @Test
    public void queryTemplateList(){
        List<Template> templates = templateMapper.queryTemplateList();
        System.out.println(templates);
    }

    @Test
    public void addTemplate(){
        Template template = new Template();
        template.setTname("电器模一证书");
        template.setTurl("空的url");
        template.setTpid(3);
        template.setTtype(2);
        Integer result = templateMapper.addTemplate(template);
        System.out.println(result);
    }

    @Test
    public void editTemplate(){
        Integer result;
        Template template = templateMapper.getTemplateById(7);
        if(template!=null)
        {
            template.setTurl("空的url");
//            template.setTisdel(0);
            result = templateMapper.editTemplate(template);
        }
        else
        {
            result = 0;
        }

        System.out.println(result);

    }

    @Test
    public void deleteTemplate(){
        Integer tid=8;
        Integer result = templateMapper.deleteTemplate(tid);
        System.out.println(result);
    }

    @Test
    public void getTemplateById(){
        Template template = templateMapper.getTemplateById(4);
        System.out.println(template);
    }
}
