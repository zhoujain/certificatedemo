package com.zhoujian.service;

import com.zhoujian.domain.Authorize;
import com.zhoujian.domain.Company;

import java.util.List;

public interface ICompanyService {

    List<Company> findAll();

    Company findByName(String name);

    Company findById(Integer id);


    Boolean save(Company company);


    String saveAuth(Authorize authorize,int i);

    List<Authorize> findAllAuth();

    List<Authorize> findAllByLikeCnumber(String cnumber);

    List<Authorize> findAllByLikeaid(String aid);

    Authorize findAById(Integer id);

    Boolean update(Authorize authorize);

    void delAuthorizeById(Integer id);
}
