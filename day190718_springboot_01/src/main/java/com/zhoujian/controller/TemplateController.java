package com.zhoujian.controller;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.Template;
import com.zhoujian.service.TemplateService;
import com.zhoujian.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            jstreeVO.setTurl(template.getTurl());
            if(template.getTpid()!=0){
                jstreeVO.setParent(template.getTpid().toString());
            }else{
                jstreeVO.setParent("#");
            }
            jstreeVO.setType(template.getTtype().toString());
            lists.add(jstreeVO);
        }
        //System.out.println(lists);
        vo.setData(lists);
        return vo;
    }

    @RequestMapping("/node_add")
    @ResponseBody
    public Integer nodeAdd(@RequestParam(value = "id")String id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent,@RequestParam(value = "turl")String turl,@RequestParam(value = "type")String type){
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId(id);
        jstreeVO.setText(text);
        jstreeVO.setParent(parent);
        jstreeVO.setTurl(turl);
        jstreeVO.setType(type);
        System.out.println(jstreeVO);
        Integer tid =templateService.nodeAdd(jstreeVO);
        //System.out.println(tid);
       return tid;

    }

    @RequestMapping("/node_delete")
    public void nodeDelete(@RequestParam(value = "id")Integer id){
        //System.out.println(id);
        templateService.nodeDelete(id);
    }

    @RequestMapping("/node_edit")
    public void nodeEdit(@RequestParam(value = "id")Integer id,@RequestParam(value = "text")String text){
        templateService.nodeUpdate(id,text);
    }

}
