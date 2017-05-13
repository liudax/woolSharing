package com.shisheng.controller;

import com.shisheng.entity.ParentCommodityType;
import com.shisheng.entity.Platform;
import com.shisheng.service.ICommodityTypeService;
import com.shisheng.service.IPlatformService;
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

}
