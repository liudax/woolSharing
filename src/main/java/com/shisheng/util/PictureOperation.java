package com.shisheng.util;

import com.shisheng.exception.PuctureUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传工具类型
 * Created by Magic on 2017/4/16.
 */
public class PictureOperation {



    public static String upload (MultipartFile file) throws PuctureUploadException{

        //判断文件是否为空
        if(file==null){
            throw new PuctureUploadException("上传文件为空");
        }
        String newFileName=null;
        String path = null;
        String type= null;
        String fileName = file.getOriginalFilename();
        type = fileName.indexOf(".") !=-1?fileName.
                substring(fileName.lastIndexOf(".")+1,fileName.length()):null;

        //判断文件类型是否为空
        if(type==null){

            throw new PuctureUploadException("文件类型为空");
        }

        //判断文件类型是否合法
        if(!("GIF".equals(type.toUpperCase()) ||
                "PNG".equals(type.toUpperCase()) ||
                "JPG".equals(type.toUpperCase())
                )){

            throw new PuctureUploadException("文件类型不合法");

        }

        synchronized (PictureOperation.class){

            newFileName = String.valueOf(System.currentTimeMillis()) +fileName;
        }

        path = "E:\\CommodityPicture\\"+newFileName;
        try{

            file.transferTo(new File(path));

        }catch (IOException e) {

           throw new PuctureUploadException("文件存放时处错");
        }
        return newFileName;
    }
}
