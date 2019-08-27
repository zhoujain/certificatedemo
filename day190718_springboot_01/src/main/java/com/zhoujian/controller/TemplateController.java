package com.zhoujian.controller;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.Template;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.TemplateService;
import com.zhoujian.service.impl.TemplateServiceImpl;
import com.zhoujian.util.FileUtil;
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
    @ResponseBody
    public String nodeDelete(@RequestParam(value = "id")Integer id,HttpServletRequest request){
        //System.out.println(id);
        templateService.nodeDelete(id);
        //删除模板
        String path = "aabb"+id+".doc";
        FileUtil.deleteFile(request,path);
        return "1";
    }

    @RequestMapping("/node_edit")
    public void nodeEdit(@RequestParam(value = "id")Integer id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent){
        templateService.nodeUpdate(id,text,parent);
    }

    @RequestMapping("/node_edit_add")
    public void nodeEditAdd(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "id")Integer id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent){
        templateService.nodeUpdate(id,text,parent);
        try {
            createFile(request,response,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateTtype")
    @ResponseBody
    public String nodeEdit(@RequestParam(value = "id")Integer id){
        //System.out.println("来了");
        templateService.UpdateTtype(id);
        return "11";
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
            createFile(request,response,tid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tid;

    }

    /**
     * 粘贴操作
     * @param request
     * @param response
     *
     * @param text
     * @param parent
     *
     * @param
     * @return
     */
    @RequestMapping("/node_add1_copy")
    @ResponseBody
    public Integer nodeAdd1Copy(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "oldId")String oldid,@RequestParam(value = "id")Integer tid,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent){
        //JstreeVO jstreeVO = new JstreeVO();
        //jstreeVO.setId(id);
        //jstreeVO.setText(text);
        //jstreeVO.setParent(parent);
        //jstreeVO.setTurl(turl);
        //System.out.println(jstreeVO);
        templateService.nodeUpdate(tid,text,parent);
        //System.out.println(tid);
        try {
             CopyFile(request,response,tid, Integer.parseInt(oldid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tid;

    }

    /**
     * 粘贴修改父节点
     *
     * @param oldid
     * @param text
     * @param parent
     * @param
     * @return
     */
    @RequestMapping("/node_add1_cut")
    //@ResponseBody
    public void nodeAdd1Cut(@RequestParam(value = "oldId")int oldid,@RequestParam(value = "text")String text,@RequestParam(value = "parent")int parent){
        //JstreeVO jstreeVO = new JstreeVO();
        //jstreeVO.setId(id);
        //jstreeVO.setText(text);
        //jstreeVO.setParent(parent);
        //jstreeVO.setTurl(turl);
        //jstreeVO.setType(type);
        //System.out.println(jstreeVO);
        System.out.println(oldid+" "+parent);
        templateService.nodeUpdatePid(oldid,parent);
        //System.out.println(tid);



    }




    /**
     * 添加模板文件时新建文件
     * @return
     */
    //@RequestMapping("/create")
    public String createFile(HttpServletRequest request, HttpServletResponse response,int ID) throws SysException {
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


    /**
     * 复制已经上传的文件
     * @param request
     * @param response
     *
     * @param ID 本身id
     * @param oldId 复制文件的id
     * @return
     * @throws SysException
     */
    public String CopyFile(HttpServletRequest request, HttpServletResponse response,int ID,int oldId) throws SysException {
        //判断参数
        String fileName="aabb"+ID+".doc";
        String oldFileName = "aabb"+oldId+".doc";
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
                        System.out.println(bytesum);
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



}
