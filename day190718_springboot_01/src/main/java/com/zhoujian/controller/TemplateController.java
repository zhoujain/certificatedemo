package com.zhoujian.controller;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.Template;
import com.zhoujian.service.TemplateService;
import com.zhoujian.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    @Qualifier("templateService")
    private TemplateService templateService;

    @RequestMapping("/templateTree")
    @ResponseBody
    public ResponseVO<JstreeVO> queryTemplateTree(){
        ResponseVO vo = new ResponseVO<List<JstreeVO>>();
        List<Template> templates = templateService.queryTemplateList();
        List<JstreeVO> lists = new ArrayList<>();
        for (Template template:templates) {
            JstreeVO jstreeVO = new JstreeVO();

            jstreeVO.setId(template.getTid().toString());
            jstreeVO.setText(template.getTname());
            if(template.getTpid()!=0){
                jstreeVO.setParent(template.getTpid().toString());
            }else{
                jstreeVO.setParent("#");
            }
            lists.add(jstreeVO);
        }
        vo.setData(lists);
        return vo;
    }

}
