package com.zhoujian.controller;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;

import com.zhoujian.domain.Ttemplate;
import com.zhoujian.exception.SysException;

import com.zhoujian.service.TtemplateService;
import com.zhoujian.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ttemplate")
public class record_TemplateController {
    @Autowired
    private TtemplateService ttemplateService;

    @RequestMapping("/ttemplateTree")
    @ResponseBody
    public ResponseVO<JstreeVO> queryTtemplateTree(){
        ResponseVO vo = new ResponseVO<List<JstreeVO>>();
        List<Ttemplate> ttemplates = ttemplateService.queryTtemplateList();
        List<JstreeVO> lists = new ArrayList<>();
        for (Ttemplate ttemplate:ttemplates) {
            JstreeVO jstreeVO = new JstreeVO();

            jstreeVO.setId(ttemplate.getTtid().toString());
            jstreeVO.setText(ttemplate.getTtname());
            jstreeVO.setTurl(ttemplate.getTturl());
            if(ttemplate.getTtpid()!=0){
                jstreeVO.setParent(ttemplate.getTtpid().toString());
            }else{
                jstreeVO.setParent("#");
            }
            jstreeVO.setType(ttemplate.getTttype().toString());
            lists.add(jstreeVO);
        }
        //System.out.println(lists);
        vo.setData(lists);
        return vo;
    }

    @RequestMapping("/tnode_add")
    @ResponseBody
    public Integer nodeAdd(@RequestParam(value = "id")String id, @RequestParam(value = "text")String text, @RequestParam(value = "parent")String parent, @RequestParam(value = "turl")String turl, @RequestParam(value = "type")String type){
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId(id);
        jstreeVO.setText(text);
        jstreeVO.setParent(parent);
        jstreeVO.setTurl(turl);
        jstreeVO.setType(type);
        System.out.println(jstreeVO);
        Integer tid =ttemplateService.nodeAdd(jstreeVO);
        //System.out.println(tid);
        return tid;

    }

    @RequestMapping("/tnode_delete")
    @ResponseBody
    public String nodeDelete(@RequestParam(value = "id")Integer id,HttpServletRequest request){
        //System.out.println(id);
        ttemplateService.nodeDelete(id);
        //删除文件
        String path = "aacc"+id+".doc";
        FileUtil.deleteFile(request,path);
        return "1";
    }

    @RequestMapping("/tnode_edit")
    public void nodeEdit(@RequestParam(value = "id")Integer id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent){
        ttemplateService.nodeUpdate(id,text,parent);
    }

    @RequestMapping("/tnode_edit_add")
    public void nodeEditAdd(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id")Integer id, @RequestParam(value = "text")String text, @RequestParam(value = "parent")String parent){
        ttemplateService.nodeUpdate(id,text,parent);
        try {
            createFileDoc(request,response,id);
            createFileXlsx(request,response,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/tupdateTtype")
    @ResponseBody
    public String nodeEdit(@RequestParam(value = "id")Integer id){
        //System.out.println("来了");
        ttemplateService.UpdateTtype(id);
        return "11";
    }

    @RequestMapping("/tnode_add1")
    @ResponseBody
    public Integer nodeAdd1(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "id")String id,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent,@RequestParam(value = "turl")String turl,@RequestParam(value = "type")String type){
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId(id);
        jstreeVO.setText(text);
        jstreeVO.setParent(parent);
        jstreeVO.setTurl(turl);
        jstreeVO.setType(type);
        System.out.println(jstreeVO);
        Integer tid =ttemplateService.nodeAdd(jstreeVO);
        //System.out.println(tid);
        try {
            createFileDoc(request,response,tid);
            createFileXlsx(request,response,tid);
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
    @RequestMapping("/tnode_add1_copy")
    @ResponseBody
    public Integer nodeAdd1Copy(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "oldId")String oldid,@RequestParam(value = "id")Integer tid,@RequestParam(value = "text")String text,@RequestParam(value = "parent")String parent){
        //JstreeVO jstreeVO = new JstreeVO();
        //jstreeVO.setId(id);
        //jstreeVO.setText(text);
        //jstreeVO.setParent(parent);
        //jstreeVO.setTurl(turl);
        //System.out.println(jstreeVO);
        ttemplateService.nodeUpdate(tid,text,parent);
        //System.out.println(tid);
        try {
            CopyFileDoc(request,response,tid, Integer.parseInt(oldid));
            CopyFileXlsx(request,response,tid,Integer.parseInt(oldid));
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
    @RequestMapping("/tnode_add1_cut")
    //@ResponseBody
    public void nodeAdd1Cut(@RequestParam(value = "oldId")int oldid,@RequestParam(value = "text")String text,@RequestParam(value = "parent")int parent){

        ttemplateService.nodeUpdatePid(oldid,parent);



    }




    /**
     * 添加模板文件时新建文件
     * @return
     */
    //@RequestMapping("/create")
    public String createFileDoc(HttpServletRequest request, HttpServletResponse response,int ID) throws SysException {
        //判断参数
        String fileName="bbaa"+ID+".doc";
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
     * 添加模板文件时新建文件
     * @return
     */
    //@RequestMapping("/create")
    public String createFileXlsx(HttpServletRequest request, HttpServletResponse response,int ID) throws SysException {
        //判断参数
        String fileName="bbaa"+ID+".xlsx";
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


                String oldPath = request.getSession().getServletContext().getRealPath("/uploads/template.xlsx");
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
    public String CopyFileDoc(HttpServletRequest request, HttpServletResponse response,int ID,int oldId) throws SysException {
        //判断参数
        String fileName="bbaa"+ID+".doc";
        String oldFileName = "bbaa"+oldId+".doc";
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
    public String CopyFileXlsx(HttpServletRequest request, HttpServletResponse response,int ID,int oldId) throws SysException {
        //判断参数
        String fileName="bbaa"+ID+".xlsx";
        String oldFileName = "bbaa"+oldId+".xlsx";
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
