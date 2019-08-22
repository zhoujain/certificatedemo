package com.zhoujian.service;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> findAll();

    Company findByName(String name);

    Company findById(Integer id);


    Boolean save(Company company);


    void saveAuth(Authorize authorize);
}
