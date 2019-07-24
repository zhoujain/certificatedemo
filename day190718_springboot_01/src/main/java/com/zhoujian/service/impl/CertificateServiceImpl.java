package com.zhoujian.service.impl;

import com.zhoujian.dao.CertificateMapper;
import com.zhoujian.domain.Certificate;
import com.zhoujian.service.CertificateService;
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
}
