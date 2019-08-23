package com.zhoujian.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期处理工具类
public class DateUtil {

    //日期转换字符串
    public static String date2String(Date date,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(date);
        return format;
    }

    //字符串转换日期
    public static Date string2Date(String str,String patt){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patt);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return parse;
    }
    //获取随机7位带字母
    public static String strMath(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String time = simpleDateFormat.format(new Date());
        char c = (char)(int)(Math.random()*26+65);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c);
        stringBuilder.append(time);
        String str =  stringBuilder.toString();
        return str;
    }

    //获取时间戳
    public static String timeStr(){
        String str =String.valueOf(new Date().getTime()/1000);
        return str;
    }

    public static void createFile(){
//       String demoing = "static/1.txt";
//        ClassPathResource classPathResource = new ClassPathResource(demoing);
//        String path = null;
//        try {
//            path = classPathResource.getURL().getPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String parentpath = path.substring(0,path.lastIndexOf(demoing));
//        //创建文件
//        File newFile = new File(parentpath,"static/2.txt");
//        FileUtils.touch
    }

    public static void main(String[] args) {
        System.out.println(timeStr()+2);
    }
}
