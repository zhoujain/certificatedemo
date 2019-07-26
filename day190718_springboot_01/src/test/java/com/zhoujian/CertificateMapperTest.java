package com.zhoujian;

import com.zhoujian.controller.CertificateController;
import com.zhoujian.dao.CertificateMapper;
import com.zhoujian.day190718_springboot_01.Day190718Springboot01Application;
import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.CertificateVo;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Day190718Springboot01Application.class)
public class CertificateMapperTest {
    @Resource(name = "certificateMapper")
    private CertificateMapper certificateMapper;
    @Autowired
    CertificateController controller;
    @Autowired
    CertificateService certificateService;
    @Autowired
    UserService userService;

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


    @Test
    public void excelWrite() throws Exception {
        //获得Excel文件输出流
        FileOutputStream out = new FileOutputStream(new File("D:/test0726/POI导出测试数据2.xls"));
        //创建excel工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建excel页
        HSSFSheet sheet = wb.createSheet("POI导出测试");
        //创建表头
        HSSFRow row1 = sheet.createRow(0);
        //创建表头的单元格
        HSSFCell cell1_1 = row1.createCell(0);
        cell1_1.setCellValue("学号");
        HSSFCell cell1_2 = row1.createCell(1);
        cell1_2.setCellValue("姓名");
        HSSFCell cell1_3 = row1.createCell(2);
        cell1_3.setCellValue("年级");
        HSSFCell cell1_4 = row1.createCell(3);
        cell1_4.setCellValue("年龄");
        HSSFCell cell1_5 = row1.createCell(4);
        cell1_5.setCellValue("性别");
        //写入一行内容：
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2_1 = row2.createCell(0);
        cell2_1.setCellValue(1);
        HSSFCell cell2_2 = row2.createCell(1);
        cell2_2.setCellValue("阿荣");
        HSSFCell cell2_3 = row2.createCell(2);
        cell2_3.setCellValue("17(3)");
        HSSFCell cell2_4 = row2.createCell(3);
        cell2_4.setCellValue(20);
        HSSFCell cell2_5 = row2.createCell(4);
        cell2_5.setCellValue("男");


        wb.write(out);
        out.close();
    }

    @Test
    public void testOutExcel(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        List<CertificateVo> certificateVoList = new ArrayList<>();
        List<Certificate> certificateList=certificateMapper.getAllCertificates();
        for (Certificate c : certificateList) {
            String uname=userService.usernameByUid(c.getUid());
            String puname=userService.usernameByUid(c.getPuid());
            certificateVoList.add(new CertificateVo(c.getCid(),c.getCnumber(),c.getCcompany(),c.getCtoolname(),c.getCmodel(),c.getCoutnumber(),c.getCmanufacturer(),c.getCdelegate(),df.format(c.getCcheckdate()).toString(),c.getCcheckdepartment(),uname,puname,df.format(c.getCprintdate()).toString(),c.getCmoney(),"<a>删除</a><a>详细</a>"));

        }
        try {
            byte[] b=controller.excelWrite(certificateVoList);
            ByteArrayInputStream in = new ByteArrayInputStream(b);
            System.out.println("123");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
