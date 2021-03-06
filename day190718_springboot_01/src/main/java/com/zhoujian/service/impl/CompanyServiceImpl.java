package com.zhoujian.service.impl;

import com.zhoujian.dao.AuthorizeMapper;
import com.zhoujian.dao.CompanyMapper;
import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Company;
import com.zhoujian.service.ICompanyService;
import com.zhoujian.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AuthorizeMapper authorizeMapper;
    @Override
    public List<Company> findAll() {
        return companyMapper.findAll();
    }

    @Override
    public Company findByName(String name) {
        return companyMapper.findByName(name);
    }

    @Override
    public Company findById(Integer id) {

        return  companyMapper.findById(id);
    }

    @Override
    public Boolean save(Company company) {
        company.setAid(DateUtil.strMath());
        return companyMapper.save(company);
    }

    @Override
    public String saveAuth(Authorize authorize,int i) {
        authorize.setCnumber(DateUtil.timeStr()+i);
        authorizeMapper.save(authorize);
        companyMapper.updateById(authorize.getCompany());
        return DateUtil.timeStr()+i;
    }

    @Override
    public List<Authorize> findAllAuth() {
        return authorizeMapper.findAll();
    }

    @Override
    public List<Authorize> findAllByLikeCnumber(String cnumber) {
        return authorizeMapper.findAllByLikeCnumber(cnumber);
    }

    @Override
    public List<Authorize> findAllByLikeaid(String aid) {
        return authorizeMapper.findAllByLikeaid(aid);
    }

    @Override
    public Authorize findAById(Integer id) {
        return authorizeMapper.findById(id);
    }

    @Override
    public Boolean update(Authorize authorize) {
        Boolean temp1 = true;
        temp1 = companyMapper.updateById(authorize.getCompany());
        temp1 =authorizeMapper.updateById(authorize);
        return temp1;
    }

    @Override
    public void delAuthorizeById(Integer id) {
        authorizeMapper.deleteById(id);
    }

}
