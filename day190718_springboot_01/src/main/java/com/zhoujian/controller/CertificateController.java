package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.CertificateVo;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CertificateController {
    @Resource(name = "certificateService")
    private CertificateService certificateService;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/index_query")
    public String to_index_query() {
        return "index_query";
    }

    @RequestMapping("/index_add")
    public String to_index_add() {
        return "index_add";
    }

    @RequestMapping("/getCertificatesDataJSON")
    @ResponseBody
    public List<CertificateVo> getCertificatesDataJSON() {
        List<CertificateVo> certificateVoList = new ArrayList<>();
        List<Certificate> certificateList=certificateService.queryAllCertificates();
        for (Certificate c : certificateList) {
            String uname=userService.usernameByUid(c.getUid());
            String puname=userService.usernameByUid(c.getPuid());
            certificateVoList.add(new CertificateVo(c.getCid(),c.getCnumber(),c.getCcompany(),c.getCtoolname(),c.getCmodel(),c.getCoutnumber(),c.getCmanufacturer(),c.getCdelegate(),c.getCcheckdate(),c.getCcheckdepartment(),uname,puname,c.getCprintdate(),c.getCmoney(),"<a>删除</a><a>详细</a>"));
        }
        return certificateVoList;
    }
}
