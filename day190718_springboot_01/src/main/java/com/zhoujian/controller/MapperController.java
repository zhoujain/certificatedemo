package com.zhoujian.controller;

import com.zhoujian.dao.UserMapper;
import com.zhoujian.domain.JstreeVO;
import com.zhoujian.domain.ResponseVO;
import com.zhoujian.domain.User;
import com.zhoujian.exception.SysException;
import com.zhoujian.service.UserService;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller

public class MapperController {
//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private UserService userService;

//    @RequestMapping("/queryUser")
//    @ResponseBody
//    public List<User> queryUser(){
//        List<User> users = userMapper.queryUserList();
//        return users;
//    }

    @RequestMapping("/findUserByUsername")
    @ResponseBody
    public User findUserByUsername(@RequestParam(value = "username")String username){
        return userService.findUserByUsername(username);
    }
//    @RequestMapping("/loginseal.zz")
//    public  String loginSeal(){
//        return
//    }
    @RequestMapping("/company")
    public String toCompany(){
        return "company";
    }
    @RequestMapping("/companyAdd")
    public String toCompanyAdd(){
        return "companyAdd";
    }
    @RequestMapping("/company_query")
    public String toCompanyQuery(){
        return "company_query";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/index_v1",method = RequestMethod.GET)
    public String index_v1(){
        return "index_v1";
    }


    @RequestMapping("/queryJsTree")
    @ResponseBody
    public ResponseVO<List<JstreeVO>> queryJsTree(User user, HttpSession session){
        ResponseVO vo = new ResponseVO<List<JstreeVO>>();
        List<JstreeVO> lists = new ArrayList<>();
        //造一些假数据
        JstreeVO jstreeVO = new JstreeVO();
        jstreeVO.setId("1");
        jstreeVO.setText("钢铁");
        jstreeVO.setParent("#");
        lists.add(jstreeVO);

        JstreeVO jstreeVO2 = new JstreeVO();
        jstreeVO2.setId("2");
        jstreeVO2.setText("石油");
        jstreeVO2.setParent("#");
        lists.add(jstreeVO2);

        JstreeVO jstreeVO3 = new JstreeVO();
        jstreeVO3.setId("3");
        jstreeVO3.setText("金子");
        jstreeVO3.setParent("1");
        lists.add(jstreeVO3);

        JstreeVO jstreeVO5 = new JstreeVO();
        jstreeVO5.setId("5");
        jstreeVO5.setText("汽油");
        jstreeVO5.setParent("2");
        lists.add(jstreeVO5);

        vo.setData(lists);
        //System.out.println(lists);
        //System.out.println(vo.toString());
        return vo;
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }
    @RequestMapping("/index_first")
    public String login_first() {

        return "index_first";
    }

    @RequestMapping("/word")
    public String word() {

        return "word";
    }

    @RequestMapping("/index_check")
    public String index_check() {

        return "index_check";
    }
    //模板打开
    @RequestMapping(value="/word1", method=RequestMethod.GET)
    public String openWord(HttpServletRequest request, Map<String,Object> map,@RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl.setSaveFilePage("/save");//设置保存的action
        //获得文件路径
        //String newPath = request.getSession().getServletContext().getRealPath("/uploads" + fileName);
        String str ="/uploads/aabb" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        return "word1";
    }

    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, HttpServletResponse response){
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile(request.getSession().getServletContext().getRealPath("/uploads") + "/" + fs.getFileName());
        fs.close();
    }
    //审核打开word
    @RequestMapping(value="/wordcheck", method=RequestMethod.GET)
    //@ResponseBody
    public String openWordCheck(HttpServletRequest request, Map<String,Object> map,@RequestParam(value = "id")String id){
        //System.out.println(map);
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("关闭","CloseFile()",21);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序
        String str ="/uploads/aabb" + id+".doc";
        String newPath = request.getSession().getServletContext().getRealPath(str);
        System.out.println(newPath);
        poCtrl.webOpen(newPath, OpenModeType.docAdmin,"张三");
        map.put("pageofficecheck",poCtrl.getHtmlCode("PageOfficeCtrl1"));

        return "wordcheck";
    }



}
