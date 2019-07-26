package com.zhoujian.service;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.Template;
import com.zhoujian.exception.SysException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TemplateService {

    /**
     * 获取模板list
     */
    List<Template> queryTemplateList();

    /**
     * 根据请求进行文件文件上传
     */
    void fileupload(HttpServletRequest request) throws SysException;

    Integer nodeAdd(JstreeVO jstreeVO);

    void nodeDelete(int tid);

    void nodeUpdate(int tid,String text);

    void nodeUpdatePid(int tid,int pid);
}
