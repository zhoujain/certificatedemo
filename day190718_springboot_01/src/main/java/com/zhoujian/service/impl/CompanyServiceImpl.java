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

@Service
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
    public void saveAuth(Authorize authorize) {
        authorize.setCnumber(DateUtil.timeStr());
        authorizeMapper.save(authorize);
        companyMapper.updateById(authorize.getCompany());
    }
}
