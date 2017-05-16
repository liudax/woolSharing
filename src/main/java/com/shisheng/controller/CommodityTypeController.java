package com.shisheng.controller;

import com.shisheng.entity.ChildCommodityType;
import com.shisheng.entity.ParentCommodityType;
import com.shisheng.service.ICommodityTypeService;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/getParentList")
    public List<Map<String,Object>> getParentList(){

        return commodityTypeService.getParentList();
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/getChildList")
    public List<Map<String,Object>> getChildList(String parentId){

        return commodityTypeService.getChildList(parentId);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/addNewType",method = RequestMethod.POST)
    public MyBoolean addNewType(@RequestParam("id") String id,
                                @RequestParam("typeName") String typeName,
                                @RequestParam(value = "parentId",required = false) String parentId,
                                @RequestParam("isParent") Integer isParent){
        int count = 0;
        if(isParent==1){
            ParentCommodityType type = new ParentCommodityType();
            type.setId(id);
            type.setTypeName(typeName);
            count = commodityTypeService.addParentType(type);
        }else{
            ChildCommodityType type = new ChildCommodityType();
            type.setId(id);
            type.setTypeName(typeName);
            type.setParentId(parentId);
            count =commodityTypeService.addChildType(type);
        }
       return count==1?new MyBoolean(true,null):new MyBoolean(false,"ID重复");
    }


    @ResponseBody
    @RequestMapping(value = "/admin/ajax/editType",method = RequestMethod.POST)
    public MyBoolean addNewType(@RequestParam("id") String id,
                                @RequestParam("newTypeName") String newTypeName,
                                @RequestParam("isParent") Integer isParent){
        try{
            if(isParent==1){
                commodityTypeService.updateParentTypeName(id,newTypeName);
            }else{
                commodityTypeService.updateChildTypeName(id,newTypeName);
            }
        }catch (Exception e){
            return new MyBoolean(false,"访问超时");
        }
        return new MyBoolean(true,null);
    }


    @ResponseBody
    @RequestMapping(value = "/admin/ajax/removeType",method = RequestMethod.POST)
    public MyBoolean addNewType(@RequestParam("removeId") String removeId,
                                @RequestParam("isParent") Integer isParent){
        int count = 0;
        if(isParent==1){
            count = commodityTypeService.deleteParentType(removeId);
        }else{
            count =commodityTypeService.deleteChildType(removeId);
        }
        return count==1?new MyBoolean(true,null):new MyBoolean(false,"访问超时");
    }
}
