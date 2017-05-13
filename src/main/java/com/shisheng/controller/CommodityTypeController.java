package com.shisheng.controller;

import com.shisheng.entity.ParentCommodityType;
import com.shisheng.service.ICommodityTypeService;
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

public class CommodityTypeController {

    @Autowired
    ICommodityTypeService commodityTypeService;

    @ResponseBody
    @RequestMapping(value = "/ajax/getAllType")
    public MyResult<List<ParentCommodityType>> getAllType(){

        return commodityTypeService.getAllType();
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/getParentType")
    public MyResult<List<ParentCommodityType>> getParentType(){

        return commodityTypeService.getAllType();
    }


}
