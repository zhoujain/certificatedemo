package com.zhoujian.dao;

import com.zhoujian.domain.Certificate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("certificateMapper")
@Mapper
public interface CertificateMapper {
    /**
     * 查询全部
     * @return
     */
    List<Certificate> getAllCertificates();

    /**
     * 按id查询
     * @param id
     * @return
     */
    Certificate getCertificateById(Integer id);

    /**
     * 添加一个证书并返回受影响的行数
     * @param certificate
     * @return
     */
    int addCertificate(Certificate certificate);

    /**
     * 按id删除
     * @param cid
     * @return
     */
    Integer delCertificate(Integer cid);

    /**
     * 更新数据库
     * @param certificate
     * @return
     */
    Integer updateCertificate(Certificate certificate);

    /**
     * 根据where条件查询
     * @param where
     * @return
     */
    List<Certificate> getCertificateByLogics(String where);

    String queryMaxCnumber();
}
