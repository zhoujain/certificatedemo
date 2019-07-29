package com.zhoujian.service.impl;

import com.zhoujian.dao.CertificateMapper;
import com.zhoujian.domain.Certificate;
import com.zhoujian.service.CertificateService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {

    @Resource(name = "certificateMapper")
    CertificateMapper certificateMapper;

    @Override
    public List<Certificate> queryAllCertificates(){
        return certificateMapper.getAllCertificates();
    }

    @Override
    public List<Certificate> queryCertificatesByLogics(String where) {
        return certificateMapper.getCertificateByLogics(where);
    }

    @Override
    public Integer delCertificateByCid(Integer cid) {
        return certificateMapper.delCertificate(cid);
    }

    @Override
    public Integer queryMaxCnumber(){
        Integer maxCnum= Integer.parseInt(certificateMapper.queryMaxCnumber());
        return maxCnum;
    }
}
