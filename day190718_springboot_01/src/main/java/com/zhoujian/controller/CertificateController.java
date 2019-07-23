package com.zhoujian.controller;

import com.zhoujian.service.CertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class CertificateController {
    @Resource(name="certificateService")
    private CertificateService certificateService;

    @RequestMapping("/123")
    public String hello(){
        return "index";
    }
}
