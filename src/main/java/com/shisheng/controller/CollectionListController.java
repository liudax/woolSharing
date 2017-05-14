package com.shisheng.controller;

import com.shisheng.entity.CollectionList;
import com.shisheng.entity.User;
import com.shisheng.service.ICollectListService;
import com.shisheng.util.MyBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/5/14.
 */

@Controller
public class CollectionListController {

    @Autowired
    ICollectListService service;

    @ResponseBody
    @RequestMapping(value = "/ajax/addCollect",method = RequestMethod.POST)
    public MyBoolean addCollect(@RequestParam("commodityId") String commodityId, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null){
            return new MyBoolean(false,"请先登陆");
        }else{
            CollectionList collection = new CollectionList();
            collection.setCommodityId(commodityId);
            collection.setUserId(user.getId());
            collection.setCollectTime(new Date());
            service.addCollect(collection);
            return  new MyBoolean(true,null);
        }

    }


    @ResponseBody
    @RequestMapping(value = "/center/ajax/getCollectList",method = RequestMethod.GET)
    public List<Map<String,Object>> getCollectList(HttpSession session){
        User user = (User)session.getAttribute("user");

        return service.getCollectList(user.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/center/ajax/deleteCollect",method = RequestMethod.POST)
    public Boolean deleteCollect(@RequestParam("id") String id, HttpSession session){
        User user = (User)session.getAttribute("user");
        return service.deleteCollect(id,user.getId());
    }
}
