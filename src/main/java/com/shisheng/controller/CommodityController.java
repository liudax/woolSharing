package com.shisheng.controller;

import com.shisheng.entity.Commodity;
import com.shisheng.entity.User;
import com.shisheng.service.ICommodityService;
import com.shisheng.util.MyValidation;
import com.shisheng.util.QueryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/18.
 */
@Controller
public class CommodityController {


    @Autowired
    ICommodityService service;


    @ResponseBody
    @RequestMapping(value = "/center/ajax/addCommodity",method = RequestMethod.POST)
    public Boolean addCommodity(Commodity commodity,HttpSession session){
        User user = (User)session.getAttribute("user");
        commodity.setUserId(user.getId());
        return  service.addCommodity(commodity);
    }

    @ResponseBody
    @RequestMapping(value ="/ajax/appendNextPage",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> appendNextPage(String search,Integer page,String path, HttpServletRequest request){
        List<Map<String,Object>> list;
        QueryPojo pojo = new QueryPojo();
        pojo.setOffset((page-1) * 5);
        if(path.startsWith("/type")){
            String typeId = path.substring(path.lastIndexOf("/")+1,path.length());
           pojo.setTypeId(typeId);
        }
        if("/zn".equals(path)){
            pojo.setShareType("站内分享");
        }
        if("/yh".equals(path)){
            pojo.setShareType("用户分享");
        }
        if("/search".equals(path)){
          pojo.setSearch(search);
        }
        return service.getDetailedList(pojo);
    }


    @ResponseBody
    @RequestMapping(value ="/ajax/addHotList",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> addHotList(){
        QueryPojo pojo = new QueryPojo();
        pojo.setOffset(0);
        pojo.setRows(7);

        return service.getDetailedList(pojo);
    }


    @ResponseBody
    @RequestMapping(value ="/center/ajax/getMyMsg",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> getMyMsg(HttpSession session,Integer page){
        QueryPojo pojo = new QueryPojo();

        User user = (User)session.getAttribute("user");
        int[] states = {0,1,2,3};
        pojo.setUserId(user.getId());
        pojo.setOffset((page-1) * 10);
        pojo.setRows(10);
        pojo.setStates(states);
        return service.getDetailedList(pojo);
    }



    private boolean addCheck(Commodity commodity){
        if(!MyValidation.isValid(commodity.getTitle())){
            return false;
        }
        if(!MyValidation.isValid(commodity.getPricePoint())){
            return false;
        }
        if(!MyValidation.isValid(commodity.getReason())){
            return false;
        }
        if(!MyValidation.isValid(commodity.getPlatformId())){
            return false;
        }
        if(!MyValidation.isValid(commodity.getPlatformId())){
            return false;
        }
        if(!MyValidation.isValid(commodity.getChildTypeId())){
            return false;
        }
        return true;

    }

}
