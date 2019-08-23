package com.zhoujian.controller;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.Record;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.ICompanyService;
import com.zhoujian.service.RecordService;
import com.zhoujian.util.WordUtil;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.DataTag;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Record_MapperController {

    @Resource(name = "certificateService")
    private CertificateService certificateService;
    @Resource(name = "recordService")
    private RecordService recordService;
    @Resource(name="companyService")
    private ICompanyService companyService;


    @RequestMapping("/record_v1")
    public String Record_v1(){
        return "record_v1";
    }
    @RequestMapping("/record_add")
    public String Record_Add(){ return "record_add";}
    @RequestMapping("/record_check")
    public String Record_Check(){ return "record_check";}
    @RequestMapping(value="/recordcheck", method= RequestMethod.GET)
    //@ResponseBody
    public String openWord_check(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序
        //poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl.setSaveFilePage("/save1");//设置保存的action
        //获得文件路径
        //String newPath = request.getSession().getServletContext().getRealPath("/uploads" + fileName);
        String str ="/uploads/bbaa" + id+".doc";
        //String str1 ="/uploads/bbaa" + id+".xlsx";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //String newPath1 = request.getSession().getServletContext().getRealPath(str1);
        System.out.println(newPath);
        // System.out.println(newPath1);
        poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        //poCtrl1.webOpen(newPath1,OpenModeType.xlsNormalEdit,"张三");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //map.put("pageoffice1",poCtrl1.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //System.out.println(map);
        map.put("id",id);
        //--- PageOffice的调用代码 结束 -----
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("wrod1");
        //mv.addObject("")
        return "recordcheck";
    }



    @RequestMapping(value="/record_word1", method= RequestMethod.GET)
    //@ResponseBody
    public String openWord(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序
        //poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl.setSaveFilePage("/save1");//设置保存的action
        //获得文件路径
        //String newPath = request.getSession().getServletContext().getRealPath("/uploads" + fileName);
        String str ="/uploads/bbaa" + id+".doc";
        //String str1 ="/uploads/bbaa" + id+".xlsx";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //String newPath1 = request.getSession().getServletContext().getRealPath(str1);
        System.out.println(newPath);
       // System.out.println(newPath1);
        poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        //poCtrl1.webOpen(newPath1,OpenModeType.xlsNormalEdit,"张三");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //map.put("pageoffice1",poCtrl1.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //System.out.println(map);
        map.put("id",id);
        //--- PageOffice的调用代码 结束 -----
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("wrod1");
        //mv.addObject("")
        return "record_word1";
    }

    @RequestMapping(value="/record_word2", method= RequestMethod.GET)
    //@ResponseBody
    public String openWord2(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        //PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序
        //poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl.setSaveFilePage("/save1");//设置保存的action
        //获得文件路径
        //String newPath = request.getSession().getServletContext().getRealPath("/uploads" + fileName);
        String str ="/uploads/bbaa" + id+".xlsx";
        //String str1 ="/uploads/bbaa" + id+".xlsx";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        //String newPath1 = request.getSession().getServletContext().getRealPath(str1);
        System.out.println(newPath);
        // System.out.println(newPath1);
        poCtrl.webOpen(newPath, OpenModeType.xlsNormalEdit,"张三");
        //poCtrl1.webOpen(newPath1,OpenModeType.xlsNormalEdit,"张三");
        map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl1.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //System.out.println(map);
        map.put("id",id);
        //--- PageOffice的调用代码 结束 -----
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("wrod1");
        //mv.addObject("")
        return "record_word2";
    }

    @RequestMapping(value="/record_add_word1")
    public String openWord_add(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id,@RequestParam(value = "cid")String cid){
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序

        Authorize authorize = companyService.findAById(Integer.parseInt(cid));
//        System.out.println(authorize);
        WordDocument doc = new WordDocument();
        DataRegion dataRegion = doc.openDataRegion("PO_sccompany");
        dataRegion.setValue(authorize.getCompany().getName());
        dataRegion = doc.openDataRegion("PO_table");
        //判断文件夹是否存在
        Object scidStr = request.getSession().getAttribute("scid");
        String wordpath ="";
        if(!StringUtils.isEmpty(scidStr)){
            //返回打开的时候
            Integer scid = Integer.parseInt(scidStr.toString());
            wordpath = request.getSession().getServletContext().getRealPath("/uploads/eTow"+scid+".doc");
            File fileword = new File(wordpath);
            if(fileword.exists()){
                dataRegion.setValue("[word]"+wordpath+"[/word]");
            }

        }else {
            //第一次打开的时候
            dataRegion = doc.openDataRegion("PO_sctoolname");
            dataRegion.setValue(authorize.getToolname());
            dataRegion = doc.openDataRegion("PO_scmodel");
            dataRegion.setValue(authorize.getModel());
            dataRegion = doc.openDataRegion("PO_scoutnumber");
            dataRegion.setValue(authorize.getOutnumber());
            dataRegion = doc.openDataRegion("PO_scmanufacturer");
            dataRegion.setValue(authorize.getManufacturer());

            DataTag dataTag = doc.openDataTag("{证书编号}");
            String cnumber = authorize.getCnumber();
            int number = authorize.getNumber();
            if(number>1){
                //模糊查询证书表结果
                int result = certificateService.queryCertificatesByLogics("where cnumber like '"+cnumber+"%'").size();
                //重新给cnumber定格式
                cnumber = cnumber+"-"+(result+1);
            }
            dataTag.setValue(cnumber);
            poCtrl.setWriter(doc);

            poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
            poCtrl.addCustomToolButton("关闭","CloseFile()",21);
            poCtrl.setSaveDataPage("/saveRecord?id="+id+"&scnumber="+cnumber);
            poCtrl.setSaveFilePage("/saveRecordFile?id="+id);//设置保存的action
            //获得文件路径
            String str ="/uploads/bbaa" + id+".doc";
            String newPath = request.getSession().getServletContext().getRealPath(str);
            System.out.println(newPath);

            poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
            map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
            //传id用于打开Excel
            map.put("id",id);

        }


        //request.getSession().setAttribute("tid",id);
        return "record_add_word1";
    }

    @RequestMapping(value="/record_add_word2")
    //@ResponseBody
    public String openWord_add(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        //PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        //poCtrl.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl1.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl1.setSaveFilePage("/saveExcelFile?id="+id);//设置保存的action
        //获得文件路径
        //String newPath = request.getSession().getServletContext().getRealPath("/uploads" + fileName);
        //String str ="/uploads/bbaa" + id+".doc";
        String str1 ="/uploads/bbaa" + id+".xlsx";
        //String newPath = request.getSession().getServletContext().getRealPath(str);
        String newPath1 = request.getSession().getServletContext().getRealPath(str1);
        //System.out.println(newPath);
        System.out.println(newPath1);
        //poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        poCtrl1.webOpen(newPath1,OpenModeType.xlsNormalEdit,"张三");
        //map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        map.put("pageoffice1",poCtrl1.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //System.out.println(map);
        int scid = Integer.parseInt(request.getSession().getAttribute("scid").toString());
        map.put("scid",scid);

        //--- PageOffice的调用代码 结束 -----
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("wrod1");
        //mv.addObject("")
        return "record_add_word2";
    }
    @RequestMapping("/save1")
    public void saveFile1(HttpServletRequest request, HttpServletResponse response){
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(request.getSession().getServletContext().getRealPath("/uploads") + "/" + fs.getFileName());
        fs.close();
    }
    @RequestMapping("/save2")
    public void saveFile2(HttpServletRequest request, HttpServletResponse response){
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(request.getSession().getServletContext().getRealPath("/uploads") + "/" + fs.getFileName());
        fs.close();
    }

    @RequestMapping(value = "/saveRecord")
    public void saveRecord(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "id")String id,@RequestParam(value="scnumber")String scnumber){
        Record record = new Record();
        record.setScnumber(scnumber);
        //取值
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_sccompany");
        record.setSccompany(dataRegion.getValue().trim());


        dataRegion = doc.openDataRegion("PO_sctoolname");
        record.setSctoolname(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scmodel");
        record.setScmodel(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scoutnumber");
        record.setScoutnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scmanufacturer");
        record.setScmanufacturer(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_sccheckdepartment");
        record.setSccheckdepartment(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_sccheckyear");
        String year = dataRegion.getValue().trim();
        dataRegion = doc.openDataRegion("PO_sccheckmonth");
        String month = dataRegion.getValue().trim();
        dataRegion = doc.openDataRegion("PO_sccheckday");
        String day = dataRegion.getValue().trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = year+"-"+month+"-"+day;
        Date date = null;
        try{
            date = format.parse(str);
        }catch(ParseException e){
            e.printStackTrace();
        }
        record.setSccheckdate(date);
        int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
        record.setUid(uid);
        int tid = Integer.parseInt(id);
        record.setTid(tid);
        System.out.println(record);
        if(recordService.queryScnumber(record.getScnumber())==null){
            Integer scid = recordService.addRecord(record);
            record.setScid(scid);
        }else{
            Integer scid = recordService.queryScnumber(record.getScnumber()).getScid();
            record.setScid(scid);
            recordService.updateRecord(record);
        }
        request.getSession().setAttribute("scid",record.getScid());
        doc.close();
    }

    public String CopyFile(HttpServletRequest request,int ID,int oldId,int type) throws SysException {
        //判断参数
        String fileName="";
        String oldFileName = "bbaa"+oldId+".doc";
        if(type==1){
            fileName="bbcc"+ID+".doc";
        }else if(type==2){
            fileName="temp"+ID+".doc";
        }else if(type==3){
            fileName="aacc"+ID+".doc";
            oldFileName="bbcc"+oldId+".doc";
        }
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

    public String CopyExcel(HttpServletRequest request,int ID,int oldId) throws SysException {
        //判断参数
        String fileName="bbcc"+ID+".xlsx";
        String oldFileName = "bbaa"+oldId+".xlsx";
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

    @RequestMapping(value = "/saveRecordFile")
    public void saveRecordFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")String id){
        int tid = Integer.parseInt(id);
        FileSaver fs = new FileSaver(request,response);
        int scid = Integer.parseInt(request.getSession().getAttribute("scid").toString());
        //创建记录文档（doc、xlsx）
        String path = request.getSession().getServletContext().getRealPath("/uploads/bbcc"+scid+".doc");
        File file = new File(path);
        if(!file.exists()){
            try{
                CopyFile(request,scid,tid,1);
                CopyExcel(request,scid,tid);
            }catch (SysException e){
                e.printStackTrace();
            }
        }
        fs.saveToFile(path);
        //创建证书文档（doc）
//        String path2 = request.getSession().getServletContext().getRealPath("/uploads/temp"+scid+".doc");
//        File file2 = new File(path2);
//        if(!file2.exists()){
//            try{
//                //创建证书文档
//                CopyFile(request,scid,tid,2);
//            }catch (SysException e){
//                e.printStackTrace();
//            }
//        }
//        fs.saveToFile(path2);
        fs.close();
    }

    @RequestMapping("/saveAsCertificate")
    @ResponseBody
    public String saveAsCertificate(HttpServletRequest request){
        Integer scid = Integer.parseInt(request.getSession().getAttribute("scid").toString());
        System.out.println(scid);
        Record record = recordService.getRecordById(scid);
        Certificate certificate = new Certificate();
        //certificate.setCid(record.getScid());
        certificate.setCname("证书名称");
        certificate.setCnumber(record.getScnumber());
        certificate.setCcompany(record.getSccompany());
        certificate.setCtoolname(record.getSctoolname());
        certificate.setCmodel(record.getScmodel());
        certificate.setCoutnumber(record.getScoutnumber());
        certificate.setCmanufacturer(record.getScmanufacturer());
        certificate.setCdelegate(record.getScdelegate());
        certificate.setCcheckdate(record.getSccheckdate());
        certificate.setCcheckdepartment(record.getSccheckdepartment());
        certificate.setUid(record.getUid());
        certificate.setPuid(record.getPuid());
        certificate.setCprintdate(record.getScprintdate());
        certificate.setCmoney(record.getScmoney());
        certificate.setTid(record.getTid());
        certificate.setCstate(1);

        //判断证书表中是否已有此编号
        Integer cid;
        Certificate c = certificateService.queryCnumber(certificate.getCnumber());
        if (c==null){
            //没有，插入新一行
            cid = certificateService.addCertificate(certificate);
        }else{
            //有，更新已有行
            cid=c.getCid();
            certificate.setCid(c.getCid());
            certificateService.editCertificate(certificate);
        }
        //更新记录表中的cid
        System.out.println(cid);
        Map<String,Integer> map = new HashMap();
        map.put("scid",scid);
        map.put("cid",cid);
        Integer result = recordService.updateCid(map);
        //System.out.println(result);
        //重命名证书文档
        try{
            CopyFile(request,cid,scid,3);
        }catch (SysException e){
            e.printStackTrace();
        }
        return "1";
    }

    @RequestMapping("/saveExcelFile")
    public void saveExcelFile(HttpServletRequest request,HttpServletResponse response,@RequestParam("id")String id){
        Integer tid = Integer.parseInt(id);
        Integer scid = Integer.parseInt(request.getSession().getAttribute("scid").toString());
        String path = request.getSession().getServletContext().getRealPath("/uploads/bbcc"+scid+".xlsx");
        File file = new File(path);
        if(!file.exists()){
            try{
                CopyExcel(request,scid,tid);
            }catch (SysException e){
                e.printStackTrace();
            }
        }
        FileSaver fs = new FileSaver(request,response);
        fs.saveToFile(path);
        fs.close();
        String wordpath = request.getSession().getServletContext().getRealPath("/uploads/eTow"+scid+".doc");

        WordUtil.excelToWord(wordpath,path,"table_1");

    }

    //详细页的控制器
    @RequestMapping(value="/record_display_word1")
    public String openWord_display(HttpServletRequest request, Map<String,Object> map,@RequestParam(value = "scid")String scid){
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序

        WordDocument doc = new WordDocument();
        DataRegion dataRegion = doc.openDataRegion("PO_table");
       //判断文件夹是否存在
        //Object scidStr = request.getSession().getAttribute("scid");
         String wordpath ="";
        //if(!StringUtils.isEmpty(scidStr)){
            //返回打开的时候
            //Integer scid = Integer.parseInt(scidStr.toString());
           wordpath = request.getSession().getServletContext().getRealPath("/uploads/eTow"+scid+".doc");
            File fileword = new File(wordpath);
           if(fileword.exists()){
                dataRegion.setValue("[word]"+wordpath+"[/word]");
            }

       // }
            poCtrl.setWriter(doc);
            poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
            poCtrl.addCustomToolButton("关闭","CloseFile()",21);
            poCtrl.setSaveDataPage("/saveRecord_display?scid="+scid);
            poCtrl.setSaveFilePage("/saveRecordFile_display");//设置保存的action
            //获得文件路径
            String str ="/uploads/bbcc" +scid+".doc";
            String newPath = request.getSession().getServletContext().getRealPath(str);
            System.out.println(newPath);

            poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
            map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
            //传id用于打开Excel
            map.put("scid",scid);

        return "record_display_word1";
    }

    @RequestMapping(value="/record_display_word2")
    //@ResponseBody
    public String openWord_display_e(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "scid")String scid){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        //PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        //poCtrl.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl1.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl1.setSaveFilePage("/saveExcelFile_display?scid="+scid);//设置保存的action

        String str1 ="/uploads/bbcc" + scid+".xlsx";
        //String newPath = request.getSession().getServletContext().getRealPath(str);
        String newPath1 = request.getSession().getServletContext().getRealPath(str1);
        //System.out.println(newPath);
        System.out.println(newPath1);
        //poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        poCtrl1.webOpen(newPath1,OpenModeType.xlsNormalEdit,"张三");
        //map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        map.put("pageoffice1",poCtrl1.getHtmlCode("PageOfficeCtrl2"));
        //map.put("pageoffice1",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //System.out.println(map);
        map.put("scid",scid);

        return "record_dispaly_word2";
    }

    //display的保存记录data
    @RequestMapping(value = "/saveRecord_display")
    public void saveRecord_display(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "scid")String scid){
        Record record = recordService.getRecordById(Integer.parseInt(scid));
        //设置number
//        record.setScnumber(scnumber);
        //取值
        com.zhuozhengsoft.pageoffice.wordreader.WordDocument doc = new com.zhuozhengsoft.pageoffice.wordreader.WordDocument(request,response);
        com.zhuozhengsoft.pageoffice.wordreader.DataRegion dataRegion = doc.openDataRegion("PO_sccompany");
        record.setSccompany(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_sctoolname");
        record.setSctoolname(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scmodel");
        record.setScmodel(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scoutnumber");
        record.setScoutnumber(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_scmanufacturer");
        record.setScmanufacturer(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_sccheckdepartment");
        record.setSccheckdepartment(dataRegion.getValue().trim());
        dataRegion = doc.openDataRegion("PO_sccheckyear");
        String year = dataRegion.getValue().trim();
        dataRegion = doc.openDataRegion("PO_sccheckmonth");
        String month = dataRegion.getValue().trim();
        dataRegion = doc.openDataRegion("PO_sccheckday");
        String day = dataRegion.getValue().trim();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = year+"-"+month+"-"+day;
        Date date = null;
        try{
            date = format.parse(str);
        }catch(ParseException e){
            e.printStackTrace();
        }
        record.setSccheckdate(date);

        int uid = Integer.parseInt(request.getSession().getAttribute("uid").toString());
        record.setUid(uid);
//        int tid = Integer.parseInt(id);
//        record.setTid(tid);
        //System.out.println(record);
//        if(recordService.queryScnumber(record.getScnumber())==null){
//            Integer scid = recordService.addRecord(record);
//            record.setScid(scid);
//        }else{
//            Integer scid = recordService.queryScnumber(record.getScnumber()).getScid();
//            record.setScid(scid);
            recordService.updateRecord(record);
//        }
        //request.getSession().setAttribute("scid",record.getScid());
        doc.close();
    }

    //display的保存记录文件
    @RequestMapping(value = "/saveRecordFile_display")
    public void saveRecordFile_display(HttpServletRequest request,HttpServletResponse response){
//        String scid = request.getSession().getAttribute("scid").toString();
        FileSaver fs= new FileSaver(request,response);
        String path = request.getSession().getServletContext().getRealPath("/uploads/"+fs.getFileName());
        fs.saveToFile(path);
        fs.close();
    }

    //display execl的保存
    @RequestMapping("/saveExcelFile_display")
    public void saveExcelFile_display(HttpServletRequest request,HttpServletResponse response,@RequestParam("scid")String scid){
        FileSaver fs= new FileSaver(request,response);
        String path = request.getSession().getServletContext().getRealPath("/uploads/"+fs.getFileName());
        fs.saveToFile(path);
        fs.close();
        String wordpath = request.getSession().getServletContext().getRealPath("/uploads/eTow"+scid+".doc");
        WordUtil.excelToWord(wordpath,path,"table_1");

    }
    //display的证书上传
    @RequestMapping("/saveAsCertificate_display")
    @ResponseBody
    public String saveAsCertificate_display(HttpServletRequest request,@RequestParam("scid")Integer scid){

        //System.out.println(scid);
        Record record = recordService.getRecordById(scid);
        Certificate certificate = new Certificate();
        //certificate.setCid(record.getScid());
        certificate.setCname("证书名称");
        certificate.setCnumber(record.getScnumber());
        certificate.setCcompany(record.getSccompany());
        certificate.setCtoolname(record.getSctoolname());
        certificate.setCmodel(record.getScmodel());
        certificate.setCoutnumber(record.getScoutnumber());
        certificate.setCmanufacturer(record.getScmanufacturer());
        certificate.setCdelegate(record.getScdelegate());
        certificate.setCcheckdate(record.getSccheckdate());
        certificate.setCcheckdepartment(record.getSccheckdepartment());
        certificate.setUid(record.getUid());
        certificate.setPuid(record.getPuid());
        certificate.setCprintdate(record.getScprintdate());
        certificate.setCmoney(record.getScmoney());
        certificate.setTid(record.getTid());
        certificate.setCstate(1);

        //判断证书表中是否已有此编号
        Integer cid;
        Certificate c = certificateService.queryCnumber(certificate.getCnumber());
        if (c==null){
            //没有，插入新一行
            cid = certificateService.addCertificate(certificate);
        }else{
            //有，更新已有行
            cid=c.getCid();
            certificate.setCid(c.getCid());
            certificateService.editCertificate(certificate);
        }
        //更新记录表中的cid
        //System.out.println(cid);
        Map<String,Integer> map = new HashMap();
        map.put("scid",scid);
        map.put("cid",cid);
        Integer result = recordService.updateCid(map);
        //System.out.println(result);
        //重命名证书文档
        try{
            CopyFile(request,cid,scid,3);
        }catch (SysException e){
            e.printStackTrace();
        }
        return "1";
    }


}
