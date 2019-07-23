package com.zhoujian.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
@Component(value = "SysExceptionResolver")
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SysException sysException = null;
        if(e instanceof SysException){
            sysException = (SysException) e;
        }else {
            sysException = new SysException("请联系管理员");
        }
        ModelAndView mv = new ModelAndView();
        //存入错误信息
        mv.addObject("errorMessage",e.getMessage());
        //跳转jsp页面
        mv.setViewName("error500");
        return mv;
    }
}
