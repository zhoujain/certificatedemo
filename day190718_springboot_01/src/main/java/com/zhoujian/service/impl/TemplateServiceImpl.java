package com.zhoujian.service.impl;

import com.zhoujian.dao.TemplateMapper;
import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.Template;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.TemplateService;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<Template> queryTemplateList(){
        return templateMapper.queryTemplateList();
    }

    /**
     * 根据请求获取文件上传
     * @param request
     */
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

    /**
     * 结点增加
     * @param jstreeVO
     * @return
     */
    @Override
    public Integer nodeAdd(JstreeVO jstreeVO) {
        Template template =new Template();
        template.setTurl(jstreeVO.getTurl());
        template.setTname(jstreeVO.getText());
        template.setTpid(Integer.valueOf(jstreeVO.getParent()));
        template.setTtype(Integer.valueOf(jstreeVO.getType()));
        templateMapper.addTemplateToTid(template);
        return template.getTid();
    }

    /**
     * 结点删除
     * @param tid
     */
    @Override
    public void nodeDelete(int tid) {
        templateMapper.deleteTemplate(tid);
    }

    /**
     * 结点重命名
     * @param tid
     * @param text
     */
    @Override
    public void nodeUpdate(int tid, String text,String parent) {
        Template template = templateMapper.getTemplateById(tid);
        template.setTname(text);
        template.setTpid(Integer.valueOf(parent));
        templateMapper.editTemplate(template);
    }

    /**
     * 根据结点修改父节点
     * @param tid
     * @param pid
     */
    @Override
    public void nodeUpdatePid(int tid, int pid) {
        Template template = templateMapper.getTemplateById(tid);
        template.setTpid(pid);
        templateMapper.editTemplate(template);
    }

    @Override
    public void UpdateTtype(int tid) {
        Template template = templateMapper.getTemplateById(tid);
        template.setTtype(3);
        templateMapper.editTemplate(template);
    }
}
