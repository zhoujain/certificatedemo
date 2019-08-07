package com.zhoujian.service;

import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.Ttemplate;
import com.zhoujian.exception.SysException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TtemplateService {
    /**
     * 获取模板list
     */
    List<Ttemplate> queryTtemplateList();

    /**
     * 根据请求进行文件文件上传
     */
    void fileupload(HttpServletRequest request) throws SysException;

    Integer nodeAdd(JstreeVO jstreeVO);

    void nodeDelete(int ttid);

    void nodeUpdate(int ttid,String text,String parent);

    void nodeUpdatePid(int ttid,int ttpid);

    void UpdateTtype(int ttid);
}
