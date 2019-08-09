package com.zhoujian;

import com.zhoujian.controller.CertificateController;
import com.zhoujian.dao.CertificateMapper;
import com.zhoujian.dao.RecordMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.CertificateVo;
import com.zhoujian.domain.Record;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class RecordMapperTest {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    CertificateController controller;
    @Autowired
    CertificateService certificateService;
    @Autowired
    UserService userService;

    @Test
    public void test_getCertificateById(){
        Record record = recordMapper.getRecordById(1);
        System.out.println(record);
    }

    @Test
    public void test_addCertificate(){
        Record record=new Record(null,"123", "212", "123", "123", "cmodel", "coutnumber", "cmanufacturer", "cdelegate", new Date(), "ccheckdepartment",1, 2, new Date(), 200.0, 12,12);
        Integer res=recordMapper.addRecord(record);
        System.out.println(res);
    }

    @Test
    public void test_delCertificate(){
        Integer res=recordMapper.delRecord(3);
        System.out.println("结果 : "+res);
    }

    @Test
    public void test_updateCertificate(){
        Record record=recordMapper.getRecordById(2);
        record.setScdelegate("已改444");
        record.setScmanufacturer("已改555");
        Integer res=recordMapper.updateRecord(record);
        System.out.println(res);
    }

    @Test
    public void test_getCertificatesByLogic(){
        List<Record> certificateList=recordMapper.getRecordByLogics("select * from tb_record where scid=5");
        System.out.println(certificateList);
    }





//    @Test
//    public void testEditCertificate(){
//        Record record = new Record();
//        record.setCid(24);
//        record.setCnumber("800136128");
//        record.setCcompany("证书单位");
//        certificate.setCtoolname("器具名称");
//        certificate.setCmodel("型号规格");
//        certificate.setCoutnumber("出厂编号");
//        certificate.setCmanufacturer("制造厂商");
//        certificate.setCdelegate("委托单号");
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String str = "2019-7-30";
//        Date date = null;
//        try {
//            date = format.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        certificate.setCcheckdate(date);
//        certificate.setCcheckdepartment("检测部门");
//        Integer result = certificateMapper.editCertificate(certificate);
//        System.out.println(result);
//        System.out.println(certificate.getCcheckdate());
//    }

    @Test
    public void testQueryCnumber(){
        String cnumber = "800136119";
        System.out.println(recordMapper.queryScnumber(cnumber));
        cnumber = "800136118";
        Record c = recordMapper.queryScnumber(cnumber);
        System.out.println(c);
    }
}
