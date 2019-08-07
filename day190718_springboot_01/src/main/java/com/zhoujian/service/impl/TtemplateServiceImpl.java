package com.zhoujian.service.impl;

import com.zhoujian.dao.TtemplateMapper;
import com.zhoujian.domain.JstreeVO;


import com.zhoujian.domain.Ttemplate;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.TtemplateService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class TtemplateServiceImpl implements TtemplateService {

    @Autowired
    private TtemplateMapper ttemplateMapper;
    @Override
    public List<Ttemplate> queryTtemplateList() {
        return ttemplateMapper.queryTtemplateList();
    }

    @Override
    public void fileupload(HttpServletRequest request) throws SysException {
        //获取工程目录我，文件的上传路径
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        //创建File对象
        File file = new File(path);
        //判断路径是否存在，如果不存在，创建改路径
        if(!file.exists()){
            file.mkdirs();
        }
        //创建磁盘文件项工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //解析request对象
        List<FileItem> list = null;
        try {
            list = fileUpload.parseRequest((RequestContext) request);
        } catch (Exception e) {
            throw new SysException("文件创建失败");
        }
        //遍历
        for(FileItem fileItem:list){
            //判断文件项是普通字段，还是上传的文件
            if(fileItem.isFormField()){

            }else {
                //上传文件项
                //获取上传文件的名称
                String filename = fileItem.getName();
                String uuid = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
                //文件名称唯一话
                filename = uuid+"_"+filename;
                //上传文件
                try {
                    fileItem.write(new File(file,filename));
                } catch (Exception e) {
                    throw new SysException("文件创建失败");
                }
                //删除临时文件
                fileItem.delete();
            }
        }
    }

    @Override
    public Integer nodeAdd(JstreeVO jstreeVO) {
        Ttemplate ttemplate =new Ttemplate();
        ttemplate.setTturl(jstreeVO.getTurl());
        ttemplate.setTtname(jstreeVO.getText());
        ttemplate.setTtpid(Integer.valueOf(jstreeVO.getParent()));
        ttemplate.setTttype(Integer.valueOf(jstreeVO.getType()));
        ttemplateMapper.addTtemplateToTtid(ttemplate);
        return ttemplate.getTtid();
    }

    @Override
    public void nodeDelete(int ttid) {
        ttemplateMapper.deleteTtemplate(ttid);
    }

    @Override
    public void nodeUpdate(int ttid, String text, String parent) {
        Ttemplate ttemplate = ttemplateMapper.getTtemplateById(ttid);
        ttemplate.setTtname(text);
        ttemplate.setTtpid(Integer.valueOf(parent));
        ttemplateMapper.editTtemplate(ttemplate);
    }

    @Override
    public void nodeUpdatePid(int ttid, int ttpid) {
        Ttemplate ttemplate = ttemplateMapper.getTtemplateById(ttid);
        ttemplate.setTtpid(ttpid);
        ttemplateMapper.editTtemplate(ttemplate);
    }

    @Override
    public void UpdateTtype(int ttid) {
        Ttemplate ttemplate = ttemplateMapper.getTtemplateById(ttid);
        ttemplate.setTttype(3);
        ttemplateMapper.editTtemplate(ttemplate);
    }
}
