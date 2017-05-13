package com.shisheng.util;

/**
 * Created by Magic on 2017/4/18.
 */
public class MyValidation {


    public static boolean isValid(Object obj){
        if(obj==null)
            return false;
        if(obj instanceof String)
            return !"".equals((String)obj);
        return true;
    }
}
