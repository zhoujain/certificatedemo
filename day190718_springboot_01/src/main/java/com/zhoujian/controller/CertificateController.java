package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.CertificateVo;
import com.zhoujian.service.CertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CertificateController {
    @Resource(name="certificateService")
    private CertificateService certificateService;

    @RequestMapping("/123")
    public String hello(){
        return "index";
    }

    @RequestMapping("/index_query")
    public String to_index_query(){
        return "index_query";
    }

    @RequestMapping("/getCertificatesDataJSON")
    @ResponseBody
    public List<CertificateVo> getCertificatesDataJSON(){
        List<CertificateVo> certificateVoList=new ArrayList<>();
        certificateVoList.add(new CertificateVo(6,"13","333","455","67","56","34","23",new Date(),"12","12","12",new Date(),23.3,"<Button>详细</Button><Button>删除</Button>"));
        certificateVoList.add(new CertificateVo(3,"12","223","425","67","56","34","23",new Date(),"12","12","12",new Date(),23.3,"<Button>详细</Button><Button>删除</Button>"));
        certificateVoList.add(new CertificateVo(2,"14","113","4523","67","56","34","23",new Date(),"12","12","12",new Date(),23.3,"<Button>详细</Button><Button>删除</Button>"));
        return certificateVoList;
    }
}
