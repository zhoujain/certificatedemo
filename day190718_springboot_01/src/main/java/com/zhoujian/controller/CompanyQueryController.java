package com.zhoujian.controller;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.QueryCertificateLogics;
import com.zhoujian.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CompanyQueryController {
    @Autowired
    private ICompanyService iCompanyService;

    @RequestMapping("toCompanyQuery")
    public String toCompanyQuery(){
        return "company_query";
    }

    @RequestMapping("findAllAuth")
    @ResponseBody
    public List<Authorize> findAllAuth(){
        List<Authorize> authorizeList= iCompanyService.findAllAuth();
        return addAction(authorizeList);
    }

    @RequestMapping("findAllAuthByLogics")
    @ResponseBody
    public List<Authorize> findAllAuthByLogics(@RequestBody QueryCertificateLogics queryCertificateLogics){

        if (queryCertificateLogics.getRet().equals("cnumber")){
            return addAction(iCompanyService.findAllByLikeCnumber(queryCertificateLogics.getTerm()));
        }else if (queryCertificateLogics.getRet().equals("aid")){
            return addAction(iCompanyService.findAllByLikeaid(queryCertificateLogics.getTerm()));
        }
        return null;
    }

    @RequestMapping("delAuthorizeById")
    @ResponseBody
    public List<Authorize> delAuthorizeById(Integer id){
        iCompanyService.delAuthorizeById(id);
        List<Authorize> authorizeList= iCompanyService.findAllAuth();
        return addAction(authorizeList);
    }

    private List<Authorize> addAction(List<Authorize> authorizeList){
        for (Authorize authorize : authorizeList) {
            authorize.setAction("<button class='btn_detail' onclick='toAnother("+authorize.getId()+")' >详情</button><button onclick='delAuthorize("+authorize.getId()+")' class='btn_del'>删除</button>");
        }
        return authorizeList;
    }
}
