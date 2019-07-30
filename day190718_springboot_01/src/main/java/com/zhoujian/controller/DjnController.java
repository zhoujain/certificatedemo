package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.exception.SysException;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
        poCtrl1.setSaveFilePage("/SaveCertificateFile");
        poCtrl1.webOpen(newPath,OpenModeType.docSubmitForm,"张三");
        map.put("po_t2c",poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        HttpSession session = request.getSession();
        session.setAttribute("tid",id);
        return "template2certificates";
    }

    /**
     * 保存证书信息到数据库
     * @param request
     * @param response
     */
    @RequestMapping("/SaveCertificate")
    public void SaveCertificate(HttpServletRequest request, HttpServletResponse response){
        //取值
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_cnumber");
//        System.out.println(dataRegion.getValue());
        Certificate certificate = new Certificate();
        certificate.setCnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ccompany");
        certificate.setCcompany(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ctoolname");
        certificate.setCtoolname(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cmodel");
        certificate.setCmodel(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_coutnumber");
        certificate.setCoutnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cmanufacturer");
        certificate.setCmanufacturer(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cdelegate");
        certificate.setCdelegate(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ccheckdate");
        //String 2 Date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = dataRegion.getValue();
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        certificate.setCcheckdate(date);
        dataRegion = doc.openDataRegion("PO_ccheckdepartment");
        certificate.setCcheckdepartment(dataRegion.getValue().trim());
        certificate.setCname("周建是sb检测证书");
        certificate.setCstate(1);
        Integer tid = Integer.parseInt(request.getSession().getAttribute("tid").toString());
        certificate.setTid(tid);//设置模板id
        certificate.setUid(1);//设置用户id
//        System.out.println(certificate);

        //插入数据库
        Integer cid = certificateService.addCertificate(certificate);
//        System.out.println(cid);
        request.getSession().setAttribute("cid",cid);
        //复制文件，保存证书
        doc.close();
    }

    /**
     * 复制文件，形成保存证书操作
     * @param request
     * @param ID
     * @param oldId
     * @return
     * @throws SysException
     */
    public String CopyFile(HttpServletRequest request,int ID,int oldId) throws SysException {
        //判断参数
        String fileName="aacc"+ID+".doc";
        String oldFileName = "aabb"+oldId+".doc";
        if (true) {
            try {

                //拷贝文件
                //if(request.getParameter("action").equals("create")){
                //创建目录

                //获取工程目录我，文件的上传路径
                String path = request.getSession().getServletContext().getRealPath("/uploads");
//                System.out.println(path);
                //创建File对象
                File file = new File(path);
                //判断路径是否存在，如果不存在，创建改路径
                if (!file.exists()) {
                    file.mkdirs();
                }

                String oldPath = request.getSession().getServletContext().getRealPath("/uploads/" + oldFileName);
                String newPath = request.getSession().getServletContext().getRealPath("/uploads/" + fileName);

                int bytesum = 0;
                int byteread = 0;
                File oldfile = new File(oldPath);
                if (oldfile.exists()) { //文件存在时
                    InputStream inStream = new FileInputStream(oldPath); //读入原文件
                    FileOutputStream fs = new FileOutputStream(newPath);
                    byte[] buffer = new byte[1444];
                    int length;
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; //字节数 文件大小
//                        System.out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                    }
                    inStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SysException("模板复制失败");
            }
        }
        return fileName;
    }

    /**
     * 保存证书到文件夹
    */
    @RequestMapping(value = "/SaveCertificateFile")
    public void SaveCertificateFile(HttpServletRequest request,HttpServletResponse response){
        int tid = Integer.parseInt(request.getSession().getAttribute("tid").toString());
        int cid = Integer.parseInt(request.getSession().getAttribute("cid").toString());
        String fileName = null;
        try {
            fileName = CopyFile(request,cid,tid);
        } catch (SysException e) {
            e.printStackTrace();
        }
        System.out.println(fileName);
        FileSaver fs = new FileSaver(request, response);
        String path1 = request.getSession().getServletContext().getRealPath("/uploads") + "/"+fileName;
        System.out.println(path1);
        fs.saveToFile(path1);
        fs.close();
    }

    @RequestMapping(value = "/content_certificate",method = RequestMethod.GET)
    public String content_certificate(HttpServletRequest request,Map<String,Object> map,@RequestParam("id")String id){
        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
        poCtrl1.setServerPage("/poserver.zz");
        WordDocument doc = new WordDocument();
        poCtrl1.setWriter(doc);
        String str ="/uploads/aacc" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        poCtrl1.setSaveDataPage("/editCertificate?cerid="+id);
        poCtrl1.setSaveFilePage("/save");
        poCtrl1.webOpen(newPath,OpenModeType.docNormalEdit,"张三");
        map.put("po_cc",poCtrl1.getHtmlCode("PageOfficeCtrl1"));

        return "content_certificate";
    }

    @RequestMapping(value = "/editCertificate")
    public void editCertificate(HttpServletRequest request, HttpServletResponse response,String cerid){
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_cnumber");
        Certificate certificate = new Certificate();
        certificate.setCnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ccompany");
        certificate.setCcompany(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ctoolname");
        certificate.setCtoolname(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cmodel");
        certificate.setCmodel(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_coutnumber");
        certificate.setCoutnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cmanufacturer");
        certificate.setCmanufacturer(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_cdelegate");
        certificate.setCdelegate(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_ccheckdate");
        //String 2 Date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = dataRegion.getValue();
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        certificate.setCcheckdate(date);
        dataRegion = doc.openDataRegion("PO_ccheckdepartment");
        certificate.setCcheckdepartment(dataRegion.getValue().trim());
        certificate.setCid(Integer.parseInt(cerid));
        System.out.println(certificate);
        Integer result = certificateService.editCertificate(certificate);
        System.out.println(result);
        doc.close();
    }
}
