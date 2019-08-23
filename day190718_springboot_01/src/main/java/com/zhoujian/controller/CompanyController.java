package com.zhoujian.controller;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Company;
import com.zhoujian.service.ICompanyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/findByName")
    @ResponseBody
    public Company findByName(@RequestParam(value = "id")Integer id){
         Company company = companyService.findById(id);
         return  company;

    }
    @RequestMapping("save")
    public ModelAndView save(Company company){
        Company company1 = companyService.findByName(company.getName());
        if(company1 !=null){
            ModelAndView mv =new ModelAndView();
            mv.addObject("message","单位已存在");
            mv.setViewName("companyAdd");
            return mv;
        }
        Boolean result = companyService.save(company);
        String message;
        if(result){
            message = "添加成功";
        }else {
            message = "添加失败";
        }
        ModelAndView mv =new ModelAndView();
        mv.addObject("message",message);
        mv.setViewName("companyAdd");
        return mv;
    }

    @RequestMapping("saveAuth")
    @ResponseBody
    public String saveAuth(@RequestBody Authorize[] authorizes){
        int i =0;
       for(Authorize authorize:authorizes){
          i++;
           companyService.saveAuth(authorize,i);

       }

        return  "1";
    }
    @RequestMapping("queryAllByLike")
    @ResponseBody
    public List<Authorize> queryAllByLike(@RequestParam(value = "cid")String cid,@RequestParam(value = "cnumber")String cnumber){
        List<Authorize> list = null;
        if(cid.equals("1")){
            //根据登记号进行查询
            list = companyService.findAllByLikeCnumber(cnumber);
        }else if(cid.equals("2")){
            //根据委托号进行查询
            list = companyService.findAllByLikeaid(cnumber);
        }else{
            //查询所有
            list = companyService.findAllAuth();
        }
        return list;
    }

}
