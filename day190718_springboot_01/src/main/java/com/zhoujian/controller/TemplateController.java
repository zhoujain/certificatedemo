package com.zhoujian.controller;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.Template;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.TemplateService;
import com.zhoujian.service.impl.TemplateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


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


    @RequestMapping("/node_add1")
    @ResponseBody
    public Integer nodeAdd1(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "id")String id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent,@RequestParam(value = "turl")String turl,@RequestParam(value = "type")String type){
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId(id);
        jstreeVO.setText(text);
        jstreeVO.setParent(parent);
        jstreeVO.setTurl(turl);
        jstreeVO.setType(type);
        System.out.println(jstreeVO);
        Integer tid =templateService.nodeAdd(jstreeVO);
        //System.out.println(tid);
        try {
            createFile(request,response,text, tid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tid;

    }




    /**
     * 添加模板文件时新建文件
     * @return
     */
    //@RequestMapping("/create")
    public String createFile(HttpServletRequest request, HttpServletResponse response, String name,int ID) throws SysException {
        //判断参数
        String fileName="aabb"+ID+".doc";
        if (true) {
            try {


                //拷贝文件
                //if(request.getParameter("action").equals("create")){
                //创建目录

                //获取工程目录我，文件的上传路径
                String path = request.getSession().getServletContext().getRealPath("/uploads");
                System.out.println(path);
                //创建File对象
                File file = new File(path);
                //判断路径是否存在，如果不存在，创建改路径
                if (!file.exists()) {
                    file.mkdirs();
                }


                String oldPath = request.getSession().getServletContext().getRealPath("/uploads/template.doc");
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
                        System.out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                    }
                    inStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SysException("模板创建失败");
            }




        }
        return fileName;
    }

}
