package com.zhoujian.controller;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Certificate;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.ICompanyService;
import com.zhoujian.service.RecordService;
import com.zhoujian.util.DateUtil;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.DataTag;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DjnController {
    @Resource(name = "certificateService")
    private CertificateService certificateService;
    @Autowired
    private RecordService recordService;
    @Resource(name="companyService")
    private ICompanyService companyService;
    //查询已经生成证书的数量
    @RequestMapping("queryCount")
    @ResponseBody
    public Integer queryCount(@RequestParam(value = "cnumber") String cnumber,@RequestParam(value = "id") Integer id){

        Integer result =0;
        //获取套数
        Integer number = companyService.findAById(id).getNumber();
        //证书数量
        Integer count = certificateService.queryCertificatesByLogics("where cnumber like '"+cnumber+"%'").size();
        if(number!=null){
            if(number>count){
                result =1;
                return  result;
            }
        }
        return  result;
    }
    @RequestMapping("/index_certificate_add")
    public String index_certificate_add(){
        return "index_certificate_add";
    }
    //证书添加
    @RequestMapping(value="/openWordwithNumchanged",method = RequestMethod.GET)
    public String openWordwithNumchanged(HttpServletRequest request, Map<String,Object> map, @RequestParam("id")String id,@RequestParam(value = "cid")String cid){
        PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        poCtrl1.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl1.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl1.setServerPage("/poserver.zz");
        WordDocument doc = new WordDocument();

        Authorize authorize = companyService.findAById(Integer.parseInt(cid));
        //获取委托号
        String cdelegate = authorize.getCompany().getAid();
        //打开数据区域
        DataTag dataTag = doc.openDataTag("{证书编号}");
        String cnumber = authorize.getCnumber();
        int number = authorize.getNumber();
        //给前台赋值
        map.put("cnumber",cnumber);
        if(number>1){

            int result = certificateService.queryCertificatesByLogics("where cnumber like '"+cnumber+"%'").size();
            //套数》证书数量
            if(number>result){
                result =result+1;
            }
            cnumber = cnumber+"-"+(result);

        }
        dataTag.setValue(cnumber);
//        dataRegion1.setEditing(false);
//        //给数据区域赋值
//        dataRegion1.setValue(cnumber);

        DataRegion dataRegion2 = doc.openDataRegion("PO_单位");
        dataRegion2.setEditing(true);
        dataRegion2.setValue(authorize.getCompany().getName());

        DataRegion dataRegion12 = doc.openDataRegion("PO_要求单位");
        dataRegion12.setEditing(true);
        dataRegion12.setValue(authorize.getCompany().getAdress());

        DataRegion dataRegion3 = doc.openDataRegion("PO_器具名称");
        dataRegion3.setEditing(true);
        dataRegion3.setValue(authorize.getToolname());

        DataRegion dataRegion4 = doc.openDataRegion("PO_型号");
        dataRegion4.setEditing(true);
        dataRegion4.setValue(authorize.getModel());

        DataRegion dataRegion13 = doc.openDataRegion("PO_设备编号");
        dataRegion13.setEditing(true);
        dataRegion13.setValue(authorize.getToolId());

        DataRegion dataRegion5 = doc.openDataRegion("PO_出厂编号");
        dataRegion5.setEditing(true);
        dataRegion5.setValue(authorize.getOutnumber());

        DataRegion dataRegion6 = doc.openDataRegion("PO_制造单位");
        dataRegion6.setEditing(true);
        dataRegion6.setValue(authorize.getManufacturer());


        DataRegion dataRegion8 = doc.openDataRegion("PO_检测部门");
        dataRegion8.setEditing(true);
        dataRegion8.setValue("");

        DataRegion dataRegion9 = doc.openDataRegion("PO_检定年");
        dataRegion9.setEditing(true);
        dataRegion9.setValue(DateUtil.getYear());

        DataRegion dataRegion10 = doc.openDataRegion("PO_鉴定月");
        dataRegion10.setEditing(true);
        dataRegion10.setValue(DateUtil.getMonth());

        DataRegion dataRegion11 = doc.openDataRegion("PO_鉴定日");
        dataRegion11.setEditing(true);
        dataRegion11.setValue(DateUtil.getDay());





        poCtrl1.setWriter(doc);

        //获取文件路径
        String str ="/uploads/aabb" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //打开word文件
        poCtrl1.setSaveDataPage("/SaveCertificate?cdelegate="+cdelegate+"&cnumber="+cnumber);
        poCtrl1.setSaveFilePage("/SaveCertificateFile");
        poCtrl1.webOpen(newPath,OpenModeType.docAdmin,"张三");
        map.put("po_t2c",poCtrl1.getHtmlCode("PageOfficeCtrl1"));
        //委托号
        map.put("cid",cid);

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
    public void SaveCertificate(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "cdelegate") String cdelegate,@RequestParam(value = "cnumber") String cnumber){
        //取值
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        //com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_编号");
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = null;
        Certificate certificate = new Certificate();
        certificate.setCdelegate(cdelegate);
        certificate.setCnumber(cnumber);
        //certificate.setCnumber(dataRegion.getValue().trim());
        //获取委托号
        try{
            dataRegion = doc.openDataRegion("PO_单位");
            certificate.setCcompany(dataRegion.getValue().trim());
        }catch (Exception e){

        }

        try {
            dataRegion = doc.openDataRegion("PO_器具名称");
            certificate.setCtoolname(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        try{
            dataRegion = doc.openDataRegion("PO_型号");
            certificate.setCmodel(dataRegion.getValue().trim());
        }catch (Exception e){

        }

        try {
            dataRegion = doc.openDataRegion("PO_出厂编号");
            certificate.setCoutnumber(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        try {
            dataRegion = doc.openDataRegion("PO_制造单位");
            certificate.setCmanufacturer(dataRegion.getValue().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*dataRegion = doc.openDataRegion("PO_cdelegate");
        certificate.setCdelegate(dataRegion.getValue().trim());*/


        try {
            dataRegion = doc.openDataRegion("PO_检定年");
            String year = dataRegion.getValue().trim();
            dataRegion = doc.openDataRegion("PO_检定月");
            String month = dataRegion.getValue().trim();
            dataRegion = doc.openDataRegion("PO_鉴定日");
            String day = dataRegion.getValue().trim();
            //String 2 Date
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = year+"-"+month+"-"+day;
            Date date = null;
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            certificate.setCcheckdate(date);
        } catch (Exception e) {

        }


        try {
            dataRegion = doc.openDataRegion("PO_检测部门");
            certificate.setCcheckdepartment(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        certificate.setCname("检测证书");
        certificate.setCstate(1);
        Integer tid = Integer.parseInt(request.getSession().getAttribute("tid").toString());
        certificate.setTid(tid);//设置模板id
        certificate.setUid(1);//设置用户id
//        System.out.println(certificate);
        if(certificateService.queryCnumber(certificate.getCnumber())==null){
            //数据库中没有数据
            //插入数据库
            Integer cid = certificateService.addCertificate(certificate);
            //request.getSession().setAttribute("cid",cid);
            certificate.setCid(cid);
        }else{
            //数据库中已有数据
            //根据cid更新数据库
            Integer cid = certificateService.queryCnumber(certificate.getCnumber()).getCid();
            certificate.setCid(cid);
            certificateService.editCertificate(certificate);
        }
        request.getSession().setAttribute("cid",certificate.getCid());
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
        FileSaver fs = new FileSaver(request,response);
        //String cid = request.getSession().getAttribute("cid").toString();
        int cid = Integer.parseInt(request.getSession().getAttribute("cid").toString());

        String path = request.getSession().getServletContext().getRealPath("/uploads")+"/aacc"+cid+".doc";
        File file = new File(path);
        if (!file.exists()){
            //复制文件，保存文件
            int tid = Integer.parseInt(request.getSession().getAttribute("tid").toString());
            //String fileName = null;
            try {
                CopyFile(request,cid,tid);
            } catch (SysException e) {
                e.printStackTrace();
            }
        }
        fs.saveToFile(path);
        fs.close();
    }

    @RequestMapping(value = "/content_certificate",method = RequestMethod.GET)
    public String content_certificate(HttpServletRequest request,Map<String,Object> map,@RequestParam("id")String id){
        PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
        poCtrl1.setServerPage("/poserver.zz");
//        WordDocument doc = new WordDocument();
//        poCtrl1.setWriter(doc);
        String str ="/uploads/aacc" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        poCtrl1.setSaveDataPage("/editCertificate?cerid="+id);
        poCtrl1.setSaveFilePage("/save");
        poCtrl1.webOpen(newPath,OpenModeType.docAdmin,"张三");
        map.put("po_cc",poCtrl1.getHtmlCode("PageOfficeCtrl1"));

        return "content_certificate";
    }
    //打开详细
    @RequestMapping(value = "/editCertificate")
    public void editCertificate(HttpServletRequest request, HttpServletResponse response,String cerid){
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = null;
        Certificate certificate = new Certificate();
        //certificate.setCnumber(dataRegion.getValue().trim());
        //获取委托号
        try{
            dataRegion = doc.openDataRegion("PO_单位");
            certificate.setCcompany(dataRegion.getValue().trim());
        }catch (Exception e){

        }

        try {
            dataRegion = doc.openDataRegion("PO_器具名称");
            certificate.setCtoolname(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        try{
            dataRegion = doc.openDataRegion("PO_型号");
            certificate.setCmodel(dataRegion.getValue().trim());
        }catch (Exception e){

        }

        try {
            dataRegion = doc.openDataRegion("PO_出厂编号");
            certificate.setCoutnumber(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        try {
            dataRegion = doc.openDataRegion("PO_制造单位");
            certificate.setCmanufacturer(dataRegion.getValue().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*dataRegion = doc.openDataRegion("PO_cdelegate");
        certificate.setCdelegate(dataRegion.getValue().trim());*/


        try {
            dataRegion = doc.openDataRegion("PO_检定年");
            String year = dataRegion.getValue().trim();
            dataRegion = doc.openDataRegion("PO_检定月");
            String month = dataRegion.getValue().trim();
            dataRegion = doc.openDataRegion("PO_鉴定日");
            String day = dataRegion.getValue().trim();
            //String 2 Date
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String str = year+"-"+month+"-"+day;
            Date date = null;
            try {
                date = format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            certificate.setCcheckdate(date);
        } catch (Exception e) {

        }


        try {
            dataRegion = doc.openDataRegion("PO_检测部门");
            certificate.setCcheckdepartment(dataRegion.getValue().trim());
        } catch (Exception e) {

        }
        certificate.setCid(Integer.parseInt(cerid));
        //进行更新
        Integer result = certificateService.editCertificate(certificate);

        doc.close();
    }

}
