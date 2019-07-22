package com.zhoujian.dao;

import com.zhoujian.domain.Certificate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("certificateMapper")
@Mapper
public interface CertificateMapper {
    List<Certificate> getAllCertificates();
    Certificate getCertificateById(Integer id);
    Integer addCertificate(Certificate certificate);
}
