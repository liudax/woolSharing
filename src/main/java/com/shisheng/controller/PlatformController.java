package com.shisheng.controller;

import com.shisheng.entity.ParentCommodityType;
import com.shisheng.entity.Platform;
import com.shisheng.service.ICommodityTypeService;
import com.shisheng.service.IPlatformService;
import com.shisheng.util.EntityIDFactory;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Magic on 2017/4/18.
 */

@Controller
public class PlatformController {

    @Autowired
    IPlatformService service;

    @ResponseBody
    @RequestMapping(value = "/ajax/getPlatformList")
    public List<Platform> getPlatformList(){

        return service.getPlatformList();
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/addPlatform")
    public Boolean addPlatform(Platform platform){
        if(platform.getRegion()==null || platform.getRegion().equals("")){platform.setRegion("国内");}
        platform.setId(EntityIDFactory.createId());
        return service.addPlatform(platform)==1?true:false;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/updatePlatform")
    public MyBoolean updatePlatform(Platform platform){
        int count = service.updatePlatform(platform);
        return count==1?new MyBoolean(true,null):new MyBoolean(false,"数据前后无变化");
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/deletePlatform")
    public Boolean deletePlatform(String id){
        return service.deletePlatformById(id)==1?true:false;
    }

}
