package com.zhoujian.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
@Component(value = "myInterceptor")
public class MyInterceptor implements HandlerInterceptor {
    /**
     * controller方法执行之前,进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentURL = request.getRequestURI();
        //System.out.println(currentURL);
        String ctxPath = request.getContextPath();
        //除掉项目名称时访问页面当前路径
        String targetURL = currentURL.substring(ctxPath.length());
        HttpSession session = request.getSession(false);
        //对当前页面进行判断，如果当前页面不为登陆页面
        if("/login".equals(targetURL)){
            return  true;
        }else {
            if("/loginUser".equals(targetURL)){
                //Servlet验证
                return true;
            }else {
                //在不为登陆页面时，进行判断，如果不是登陆页面也没有Session则跳转登陆页面
                if(session==null||session.getAttribute("username")==null){
                    response.sendRedirect("/login");
                    return false;
                }else {
                    return true;
                }
            }
        }
    }
}
