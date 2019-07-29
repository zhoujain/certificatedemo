package com.zhoujian.controller;

import com.zhoujian.service.CertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import com.zhuozhengsoft.pageoffice.*;
import com.zhuozhengsoft.pageoffice.wordwriter.*;

@Controller
public class DjnController {
    @Resource(name = "certificateService")
    private CertificateService certificateService;

    @RequestMapping("/index_certificate_add")
    public String index_certificate_add(){
        return "index_certificate_add";
    }

    @RequestMapping(value="/openWordwithNumchanged",method = RequestMethod.GET)
    public String openWordwithNumchanged(HttpServletRequest request, Map<String,Object> map, @RequestParam("id")String id){
        PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        poCtrl1.setServerPage("/poserver.zz");
        WordDocument doc = new WordDocument();

        //打开数据区域
        DataRegion dataRegion1 = doc.openDataRegion("PO_test");
        Integer cnum = certificateService.queryMaxCnumber()+1;
        //给数据区域赋值
        dataRegion1.setValue(cnum.toString());
        poCtrl1.setWriter(doc);

        //隐藏菜单栏
        poCtrl1.setMenubar(false);
        //隐藏工具栏
        poCtrl1.setCustomToolbar(false);
        //获取文件路径
        String str ="/uploads/aabb" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //打开word文件
        poCtrl1.webOpen(newPath,OpenModeType.docNormalEdit,"张三");
        map.put("po_t2c",poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        return "template2certificates";
    }
}
