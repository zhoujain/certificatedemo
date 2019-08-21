package com.zhoujian.controller;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.zhoujian.domain.Company;
import com.zhoujian.service.ICompanyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;
    @RequestMapping("/queryLike")
    @ResponseBody
    public Map queryAll(){
        List<Company> list = companyService.findAll();
        Map map = new HashMap();
        map.put("value",list);
        return map;
    }
}
