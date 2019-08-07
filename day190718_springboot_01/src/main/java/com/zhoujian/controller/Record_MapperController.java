package com.zhoujian.controller;

import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class Record_MapperController {
    @RequestMapping("/record_v1")
    public String Record_v1(){
        return "record_v1";
    }
    @RequestMapping("/record_add")
    public String Record_Add(){ return "record_add";}

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

    @RequestMapping(value="/record_add_word1", method= RequestMethod.GET)
    //@ResponseBody
    public String openWord_add(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id,@RequestParam(value = "cid")String cid){
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
        return "record_add_word1";
    }

    @RequestMapping(value="/record_add_word2", method= RequestMethod.GET)
    //@ResponseBody
    public String openWord1(HttpServletRequest request, Map<String,Object> map, @RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        //PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        PageOfficeCtrl poCtrl1=new PageOfficeCtrl(request);
        //poCtrl.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.setServerPage("/poserver.zz");//设置授权程序
        poCtrl1.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl1.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl1.setSaveFilePage("/save2");//设置保存的action
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
        map.put("id",id);

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
}
