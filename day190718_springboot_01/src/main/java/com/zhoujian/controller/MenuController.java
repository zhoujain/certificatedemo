package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.Menu;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MenuController {
    @Resource(name="menuService")
    private MenuService menuService;

    @Resource(name = "certificateService")
    private CertificateService certificateService;

    @RequestMapping("/queryCerBy")
    @ResponseBody
    public List<Certificate> queryCerBy(@RequestParam(value = "cid")String cid, @RequestParam(value = "cnumber")String cnumber){
        //ModelAndView mv = new ModelAndView();
        //mv.setViewName("index_print");
        List<Certificate> list=null;
        if(cid.equals("1")){
            list =certificateService.queryCertificatesByLogics("where cnumber like '%"+cnumber+"%'");
        }else if(cid.equals("2")){
            list =certificateService.queryCertificatesByLogics("where cdelegate like '%"+cnumber+"%'");
        }else {
            list =certificateService.queryAllCertificates();
        }
        //mv.addObject("CerList",list);
        return list;
    }

    @RequestMapping("/index_print")
    public String Index_print(){
        return "index_print";
    }

    @RequestMapping("/Print")
    public String Print(Model model, @RequestParam(value = "id")Integer id){
        model.addAttribute("id",id);
        return "Print";
    }


}
