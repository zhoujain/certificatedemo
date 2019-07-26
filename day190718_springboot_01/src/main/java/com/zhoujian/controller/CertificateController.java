package com.zhoujian.controller;

import com.zhoujian.domain.Certificate;
import com.zhoujian.domain.CertificateVo;
import com.zhoujian.domain.QueryCertificateLogics;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@Controller
public class CertificateController {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    @Resource(name = "certificateService")
    private CertificateService certificateService;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/index_query")
    public String to_index_query() {
        return "index_query";
    }

    @RequestMapping("/index_add")
    public String to_index_add() {
        return "index_add";
    }

    @RequestMapping("/getCertificatesDataJSON")
    @ResponseBody
    public List<CertificateVo> getCertificatesDataJSON() {
        List<CertificateVo> certificateVoList = new ArrayList<>();
        List<Certificate> certificateList=certificateService.queryAllCertificates();
        for (Certificate c : certificateList) {
            String uname=userService.usernameByUid(c.getUid());
            String puname=userService.usernameByUid(c.getPuid());
            certificateVoList.add(new CertificateVo(c.getCid(),c.getCnumber(),c.getCcompany(),c.getCtoolname(),c.getCmodel(),c.getCoutnumber(),c.getCmanufacturer(),c.getCdelegate(),df.format(c.getCcheckdate()).toString(),c.getCcheckdepartment(),uname,puname,df.format(c.getCprintdate()).toString(),c.getCmoney(),"<a>删除</a><a>详细</a>"));

        }
        return certificateVoList;
    }

    /**
     *
     * @param logicsList
     * @return 返回根据条件查询后的Certificate json
     */
    @RequestMapping("/getCertificatesDataJSONByLogics")
    @ResponseBody
    public List<CertificateVo> getCertificatesDataJSONByLogics(@RequestBody List<QueryCertificateLogics> logicsList) {
        List<CertificateVo> certificateVoList = new ArrayList<>();

        String where="where ";
        //拼接字符串
        for (QueryCertificateLogics logics : logicsList) {
            if (logics.getCom().equals("like")){
                if (logics.getLg().equals("firstlg")){
                    where+=logics.getRet()+" like \"%"+logics.getTerm()+"%\"";
                }
                else {
                    where+=" "+logics.getLg()+" "+logics.getRet()+" like \"%"+logics.getTerm()+"%\"";
                }
            }
            else {
                if (logics.getLg().equals("firstlg")){
                    where+=logics.getRet()+" "+logics.getCom()+" \""+logics.getTerm()+"\"";
                }
                else {
                    where+=" "+logics.getLg()+" "+logics.getRet()+" "+logics.getCom()+" \""+logics.getTerm()+"\"";
                }
            }
        }
        List<Certificate> certificateList=certificateService.queryCertificatesByLogics(where);
        for (Certificate c : certificateList) {
            String uname=userService.usernameByUid(c.getUid());
            String puname=userService.usernameByUid(c.getPuid());
            certificateVoList.add(new CertificateVo(c.getCid(),c.getCnumber(),c.getCcompany(),c.getCtoolname(),c.getCmodel(),c.getCoutnumber(),c.getCmanufacturer(),c.getCdelegate(),df.format(c.getCcheckdate()).toString(),c.getCcheckdepartment(),uname,puname,df.format(c.getCprintdate()).toString(),c.getCmoney(),"<a>删除</a><a>详细</a>"));
        }
        return certificateVoList;
    }

    /*@RequestMapping("/tableJsonToExcel")
    @ResponseBody
    public String tableJsonToExcel(@RequestBody List<CertificateVo> certificateVoList){
        try {
            FileOutputStream outputStream1=excelWrite(certificateVoList);

            return "导出成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "出现异常，导出失败";
        }
    }*/

    @RequestMapping("/downloadExcel")
    public ResponseEntity<byte[]> downloadExcel(@RequestBody List<CertificateVo> certificateVoList) throws IOException {

        byte[] bytes = new byte[0];
        try {
            bytes=excelWrite(certificateVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss");
        String fileName=df.format(new Date());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
    }

    /**
     * 将对象数组转为excel文件
     * 返回 输入流
     */
    public byte[] excelWrite(List<CertificateVo> certificateVoList) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        //创建excel页
        HSSFSheet sheet = wb.createSheet(df.format(new Date())+"查询证书");
        //创建表头
        HSSFRow tHeadRow = sheet.createRow(0);
        //创建表头的单元格
        HSSFCell tHeadRow_1 = tHeadRow.createCell(0);
        tHeadRow_1.setCellValue("序号");
        HSSFCell tHeadRow_2 = tHeadRow.createCell(1);
        tHeadRow_2.setCellValue("证书编号");
        HSSFCell tHeadRow_3 = tHeadRow.createCell(2);
        tHeadRow_3.setCellValue("证书单位");
        HSSFCell tHeadRow_4 = tHeadRow.createCell(3);
        tHeadRow_4.setCellValue("器具名称");
        HSSFCell tHeadRow_5 = tHeadRow.createCell(4);
        tHeadRow_5.setCellValue("型号规格");
        HSSFCell tHeadRow_6 = tHeadRow.createCell(5);
        tHeadRow_6.setCellValue("出厂编号");
        HSSFCell tHeadRow_7 = tHeadRow.createCell(6);
        tHeadRow_7.setCellValue("制造厂商");
        HSSFCell tHeadRow_8 = tHeadRow.createCell(7);
        tHeadRow_8.setCellValue("委托单号");
        HSSFCell tHeadRow_9 = tHeadRow.createCell(8);
        tHeadRow_9.setCellValue("检定日期");
        HSSFCell tHeadRow_10 = tHeadRow.createCell(9);
        tHeadRow_10.setCellValue("检测部门");
        HSSFCell tHeadRow_11 = tHeadRow.createCell(10);
        tHeadRow_11.setCellValue("添加人");
        HSSFCell tHeadRow_12 = tHeadRow.createCell(11);
        tHeadRow_12.setCellValue("打印人");
        HSSFCell tHeadRow_13 = tHeadRow.createCell(12);
        tHeadRow_13.setCellValue("打印日期");
        HSSFCell tHeadRow_14 = tHeadRow.createCell(13);
        tHeadRow_14.setCellValue("检测费");

        int row_index=1;
        //写入主体内容：
        for (CertificateVo certificateVo : certificateVoList) {
            HSSFRow newRow = sheet.createRow(row_index);
            for (int i=0;i<14;i++){
                HSSFCell newCell=newRow.createCell(i);
                switch (i){
                    case 0:
                        newCell.setCellValue(certificateVo.getCid());
                        break;
                    case 1:
                        newCell.setCellValue(certificateVo.getCnumber());
                        break;
                    case 2:
                        newCell.setCellValue(certificateVo.getCcompany());
                        break;
                    case 3:
                        newCell.setCellValue(certificateVo.getCtoolname());
                        break;
                    case 4:
                        newCell.setCellValue(certificateVo.getCmodel());
                        break;
                    case 5:
                        newCell.setCellValue(certificateVo.getCoutnumber());
                        break;
                    case 6:
                        newCell.setCellValue(certificateVo.getCmanfacturer());
                        break;
                    case 7:
                        newCell.setCellValue(certificateVo.getCdelegate());
                        break;
                    case 8:
                        newCell.setCellValue(certificateVo.getCcheckdate());
                        break;
                    case 9:
                        newCell.setCellValue(certificateVo.getCcheckdepartment());
                        break;
                    case 10:
                        newCell.setCellValue(certificateVo.getUname());
                        break;
                    case 11:
                        newCell.setCellValue(certificateVo.getPuname());
                        break;
                    case 12:
                        newCell.setCellValue(certificateVo.getCprintdate());
                        break;
                    case 13:
                        newCell.setCellValue(certificateVo.getCmoney());
                        break;
                }
            }
            row_index++;
        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = os.toByteArray();
        return b;
    }


    /**
     * 文件对象转byte[]数组
     * @param file
     * @return*/
    /*public static byte[] fileToByteArray(File file) {

        byte[] buffer = null;
        FileInputStream fis;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
            if(file.exists()) {
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }*/
}
