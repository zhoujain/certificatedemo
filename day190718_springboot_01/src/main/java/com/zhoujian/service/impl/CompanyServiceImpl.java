package com.zhoujian.service.impl;

import com.zhoujian.dao.CompanyMapper;
import com.zhoujian.domain.Company;
import com.zhoujian.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Override
    public List<Company> findAll() {
        return companyMapper.findAll();
    }
}
