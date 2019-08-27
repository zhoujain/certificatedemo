package com.zhoujian.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

//文件处理类
public class FileUtil {

    //文件删除
    public static Boolean deleteFile(HttpServletRequest request,String path){
        path = request.getSession().getServletContext().getRealPath("/uploads/"+path);
        File file = new File(path);
        if(file.exists()){
            file.delete();
            return true;
        }
        return  false;
    }
}
