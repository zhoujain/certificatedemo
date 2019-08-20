package com.zhoujian.util;

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
}
