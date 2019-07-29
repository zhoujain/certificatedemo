package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.service.CertificateService;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.zhuozhengsoft.pageoffice.*;

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
        DataRegion dataRegion1 = doc.openDataRegion("PO_cnumber");
        Integer cnum = certificateService.queryMaxCnumber()+1;
        dataRegion1.setEditing(true);
        //给数据区域赋值
        dataRegion1.setValue(cnum.toString());

        DataRegion dataRegion2 = doc.openDataRegion("PO_ccompany");
        dataRegion2.setEditing(true);
        dataRegion2.setValue("");

        DataRegion dataRegion3 = doc.openDataRegion("PO_ctoolname");
        dataRegion3.setEditing(true);
        dataRegion3.setValue("");

        DataRegion dataRegion4 = doc.openDataRegion("PO_cmodel");
        dataRegion4.setEditing(true);
        dataRegion4.setValue("");

        DataRegion dataRegion5 = doc.openDataRegion("PO_coutnumber");
        dataRegion5.setEditing(true);
        dataRegion5.setValue("");

        DataRegion dataRegion6 = doc.openDataRegion("PO_cmanufacturer");
        dataRegion6.setEditing(true);
        dataRegion6.setValue("");

        DataRegion dataRegion7 = doc.openDataRegion("PO_cdelegate");
        dataRegion7.setEditing(true);
        dataRegion7.setValue("");

        DataRegion dataRegion8 = doc.openDataRegion("PO_ccheckdate");
        dataRegion8.setEditing(true);
        dataRegion8.setValue("");

        DataRegion dataRegion9 = doc.openDataRegion("PO_ccheckdepartment");
        dataRegion9.setEditing(true);
        dataRegion9.setValue("");

        poCtrl1.setWriter(doc);

        //隐藏菜单栏
        poCtrl1.setMenubar(false);
        //隐藏工具栏
        poCtrl1.setCustomToolbar(false);
        //获取文件路径
        String str ="/uploads/aabb" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //打开word文件
        poCtrl1.setSaveDataPage("/SaveCertificate");
        poCtrl1.webOpen(newPath,OpenModeType.docSubmitForm,"张三");
        map.put("po_t2c",poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        HttpSession session = request.getSession();
        session.setAttribute("tid",id);
        return "template2certificates";
    }

    @RequestMapping("/SaveCertificate")
    public void SaveCertificate(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        //取值
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_cnumber");
//        System.out.println(dataRegion.getValue());
        Certificate certificate = new Certificate();
        certificate.setCnumber(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_ccompany");
        certificate.setCcompany(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_ctoolname");
        certificate.setCtoolname(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_cmodel");
        certificate.setCmodel(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_coutnumber");
        certificate.setCoutnumber(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_cmanufacturer");
        certificate.setCmanufacturer(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_cdelegate");
        certificate.setCdelegate(dataRegion.getValue());
        dataRegion = doc.openDataRegion("PO_ccheckdate");
        //String 2 Date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = dataRegion.getValue();
        Date date = format.parse(str);
        certificate.setCcheckdate(date);
        dataRegion = doc.openDataRegion("PO_ccheckdepartment");
        certificate.setCcheckdepartment(dataRegion.getValue());
        certificate.setCname("周建是sb检测证书");
        certificate.setCstate(1);
        certificate.setTid(Integer.parseInt(request.getSession().getAttribute("tid").toString()));//设置模板id
        certificate.setUid(1);//设置用户id
//        System.out.println(certificate);

        //插入数据库
        Integer cid = certificateService.addCertificate(certificate);
        System.out.println(cid);
        //复制文件，保存证书
    }
}
