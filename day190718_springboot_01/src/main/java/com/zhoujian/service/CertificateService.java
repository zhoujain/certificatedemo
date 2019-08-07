package com.zhoujian.service;

import com.zhoujian.domain.Certificate;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CertificateService {
    public List<Certificate> queryAllCertificates();

    List<Certificate> queryCertificatesByLogics(String where);

    Integer delCertificateByCid(Integer cid);

    Integer queryMaxCnumber();

    Integer addCertificate(Certificate certificate);

    Integer editCertificate(Certificate certificate);

    Certificate queryCnumber(String cnumber);
}
