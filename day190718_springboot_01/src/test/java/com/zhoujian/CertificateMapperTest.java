package com.zhoujian;

import com.zhoujian.dao.CertificateMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.Certificate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class CertificateMapperTest {
    @Resource(name = "certificateMapper")
    private CertificateMapper certificateMapper;

    @Test
    public void test_getCertificateById(){
        Certificate certificate = certificateMapper.getCertificateById(1);
        System.out.println(certificate);
    }

    @Test
    public void test_addCertificate(){
        Certificate certificate=new Certificate(null,"123", "212", "123", "123", "cmodel", "coutnumber", "cmanufacturer", "cdelegate", new Date(), "ccheckdepartment",1, 2, new Date(), 200.0, 12,12);
        Integer res=certificateMapper.addCertificate(certificate);
        System.out.println(res);
    }

    @Test
    public void test_delCertificate(){
        Integer res=certificateMapper.delCertificate(3);
        System.out.println("结果 : "+res);
    }

    @Test
    public void test_updateCertificate(){
        Certificate certificate=certificateMapper.getCertificateById(2);
        certificate.setCdelegate("已改444");
        certificate.setCmanufacturer("已改555");
        Integer res=certificateMapper.updateCertificate(certificate);
        System.out.println(res);
    }

    @Test
    public void test_getCertificatesByLogic(){
        List<Certificate> certificateList=certificateMapper.getCertificateByLogics("select * from tb_certificate where cid=5");
        System.out.println(certificateList);
    }

}
