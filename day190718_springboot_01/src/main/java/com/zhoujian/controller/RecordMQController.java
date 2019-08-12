package com.zhoujian.controller;

import com.zhoujian.domain.*;
import com.zhoujian.service.CertificateService;
import com.zhoujian.service.RecordMQService;
import com.zhoujian.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

@Controller
public class RecordMQController {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

    @Autowired
    private RecordMQService recordMQService;
    @Autowired
    private UserService userService;

    @RequestMapping("/record_query")
    public String to_record_query() {
        return "record_query";
    }

    @RequestMapping("/record_update")
    public String to_record_update() {
        return "record_update";
    }
    @RequestMapping("/record_upload")
    public String to_record_upload() {
        return "record_upload";
    }


    /**
     * 查询全部数据以json字符串形式返回
     */
    @RequestMapping("/getRecordDataJSON")
    @ResponseBody
    public List<RecordVo> getRecordDataJSON(HttpSession session) {

        //因为本次是全部查询，所以将session里的where对应的值置为“特殊值”，该特殊值的意义在于表示查询所有Certificate数据
        //session里的where是为了在后台导出查询所得表格数据而准备的
        session.setAttribute("where", "queryAllRecord");
        List<Record> recordList = recordMQService.queryAllRecord();
        return toVoList(recordList);
    }

    /**
     * 按id删除Record
     * @param cid
     * @param session
     * @return
     */
    @RequestMapping("/delRecordByCid")
    @ResponseBody
    public List<RecordVo> delRecordByCid(Integer cid,HttpSession session){

        recordMQService.delRecordByCid(cid);

        String where = (String) session.getAttribute("where");
        List<Record> recordList;
        //进行判断，两种情况。一种查询全部，一种按条件查询
        if (where.equals("queryAllRecord")){
            recordList=recordMQService.queryAllRecord();
        }else {
            recordList = recordMQService.queryRecordByLogics(where);
        }

        return toVoList(recordList);
    }

    /**
     *
     * @param logicsList
     * @return 返回根据条件查询后的Certificate json
     */
    @RequestMapping("/getRecordDataJSONByLogics")
    @ResponseBody
    public List<RecordVo> getRecordDataJSONByLogics(@RequestBody List<QueryCertificateLogics> logicsList, HttpSession session) {

        String where="where ";
        //根据从前端所得查询逻辑拼接字符串
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
        session.setAttribute("where",where);
        List<Record> recordList=recordMQService.queryRecordByLogics(where);
        //将一些字段换为前端所需要的字段

        return toVoList(recordList);
    }

    /**
     * 响应前台的下载请求，读取session里的查询条件并将结果回馈前端进行下载
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadRecordExcel")
    public ResponseEntity<byte[]> downloadExcel(HttpSession session) throws IOException {

        List<RecordVo> recordVoList=new ArrayList<>();

        String where = (String) session.getAttribute("where");
        List<Record> recordList;
        //进行判断，两种情况。一种查询全部，一种按条件查询
        if (where.equals("queryAllRecord")){
            recordList=recordMQService.queryAllRecord();
        }else {
            recordList = recordMQService.queryRecordByLogics(where);
        }

        recordVoList=toVoList(recordList);

        //将查询所得的列表通过poi转为byte[]
        byte[] bytes = new byte[0];
        try {
            bytes=excelWrite(recordVoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss");
        String fileName=df.format(new Date());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName+".xls");

        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
    }

    /**
     * 将对象数组转为excel文件
     * 返回 输入流
     */
    public byte[] excelWrite(List<RecordVo> recordVoList) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        //创建excel页
        HSSFSheet sheet = wb.createSheet(df.format(new Date())+"查询原始记录");
        //创建表头
        HSSFRow tHeadRow = sheet.createRow(0);
        //创建表头的单元格
        HSSFCell tHeadRow_0 = tHeadRow.createCell(0);
        tHeadRow_0.setCellValue("序号");
        HSSFCell tHeadRow_1 = tHeadRow.createCell(1);
        tHeadRow_1.setCellValue("委托单号");
        HSSFCell tHeadRow_2 = tHeadRow.createCell(2);
        tHeadRow_2.setCellValue("证书编号");
        HSSFCell tHeadRow_3 = tHeadRow.createCell(3);
        tHeadRow_3.setCellValue("证书单位");
        HSSFCell tHeadRow_4 = tHeadRow.createCell(4);
        tHeadRow_4.setCellValue("器具名称");
        HSSFCell tHeadRow_5 = tHeadRow.createCell(5);
        tHeadRow_5.setCellValue("型号规格");
        HSSFCell tHeadRow_6 = tHeadRow.createCell(6);
        tHeadRow_6.setCellValue("出厂编号");
        HSSFCell tHeadRow_7 = tHeadRow.createCell(7);
        tHeadRow_7.setCellValue("制造厂商");

        HSSFCell tHeadRow_8 = tHeadRow.createCell(8);
        tHeadRow_8.setCellValue("检测部门");
        HSSFCell tHeadRow_9 = tHeadRow.createCell(9);
        tHeadRow_9.setCellValue("添加人");
        HSSFCell tHeadRow_10 = tHeadRow.createCell(10);
        tHeadRow_10.setCellValue("打印日期");

        int row_index=1;
        //写入主体内容：
        for (RecordVo recordVo : recordVoList) {
            HSSFRow newRow = sheet.createRow(row_index);
            for (int i=0;i<=10;i++){
                HSSFCell newCell=newRow.createCell(i);
                switch (i){
                    case 0:
                        newCell.setCellValue(recordVo.getScid());
                        break;
                    case 1:
                        newCell.setCellValue(recordVo.getScdelegate());
                        break;
                    case 2:
                        newCell.setCellValue(recordVo.getScnumber());
                        break;
                    case 3:
                        newCell.setCellValue(recordVo.getSccompany());
                        break;
                    case 4:
                        newCell.setCellValue(recordVo.getSctoolname());
                        break;
                    case 5:
                        newCell.setCellValue(recordVo.getScmodel());
                        break;
                    case 6:
                        newCell.setCellValue(recordVo.getScoutnumber());
                        break;
                    case 7:
                        newCell.setCellValue(recordVo.getScmanufacturer());
                        break;
                    case 8:
                        newCell.setCellValue(recordVo.getSccheckdepartment());
                        break;
                    case 9:
                        newCell.setCellValue(recordVo.getUname());
                        break;
                    case 10:
                        newCell.setCellValue(recordVo.getScprintdate());
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
     * 因为前端所需要的表格数据的一些列名与后台直接查询所得数据中的字段不对应
     * 所以需要转换一些字段名及对应的数据
     */
    private List<RecordVo> toVoList(List<Record> recordList) {
         /*因为前端所需要的表格数据的一些列名与后台直接查询所得数据中的字段不对应
        所以需要转换一些字段名及对应的数据*/
        List<RecordVo> recordVoList = new ArrayList<>();
        //这里开始循环原列表进行转换
        for (Record r : recordList) {
            String unameByUid = userService.usernameByUid(r.getUid());
//            String punameByUid=userService.usernameByUid(c.getPuid());
            Integer scid = r.getScid() == null ? 0 : r.getScid();
            String scnumber = r.getScnumber() == null ? "无" : r.getScnumber();
            String sccompany = r.getSccompany() == null ? "无" : r.getSccompany();
            String sctoolname = r.getSctoolname() == null ? "无" : r.getSctoolname();
            String scmodel = r.getScmodel() == null ? "无" : r.getScmodel();
            String scoutnumber = r.getScoutnumber() == null ? "无" : r.getScoutnumber();
            String scmanufacturer = r.getScmanufacturer() == null ? "无" : r.getScmanufacturer();
            String scdelegate = r.getScdelegate() == null ? "无" : r.getScdelegate();
//            String scccheckdate=r.getSccheckdate()==null?"无": df.format(r.getCcheckdate());
            String sccheckdepartment = r.getSccheckdepartment() == null ? "无" : r.getSccheckdepartment();
            String uname = unameByUid == null ? "无" : unameByUid;
//            String puname=punameByUid==null?"无":punameByUid;
            String cprintdate = r.getScprintdate() == null ? "无" : df.format(r.getScprintdate());
//            String cmoney=r.getScmoney()==null?"无": r.getCmoney().toString();
            recordVoList.add(new RecordVo(scid, scnumber, sccompany, sctoolname, scmodel, scoutnumber, scmanufacturer, scdelegate, sccheckdepartment, uname,cprintdate,"<button onclick='toAnother(" + r.getScid() + ")' style=\"border:1px solid blue;color:blue\">详细</button>&nbsp;<button onclick=\"delCertificate(" + r.getScid() + ")\" style=\"border:1px solid red;color:red\">删除</button>"));
        }

        return recordVoList;
    }
}
