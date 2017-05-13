package com.shisheng.dao;

import org.junit.Test;


import java.io.File;
import java.io.IOException;


public class UserMapperTest {


    @Test
    public  void imagesTest() throws IOException {
        File file = new File("E:\\CommodityPicture\\图片1.png");
        String fileName = file.getName();
        System.out.println(fileName);
    }

}