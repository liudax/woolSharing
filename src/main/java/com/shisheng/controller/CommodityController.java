package com.shisheng.controller;

import com.shisheng.entity.Commodity;
import com.shisheng.entity.User;
import com.shisheng.service.ICommodityService;
import com.shisheng.util.EntityIDFactory;
import com.shisheng.util.MyValidation;
import com.shisheng.util.QueryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/18.
 */
@Controller
public class CommodityController {


    @Autowired
    ICommodityService service;

    /**
     * 修改优惠（管理员，用户）
     * @param newCommodity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/center/ajax/addCommodity","/admin/ajax/addCommodity"},method = RequestMethod.POST)
    public Boolean addCommodity(Commodity newCommodity,HttpServletRequest request){
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        if(path.startsWith("/admin")){
            User user = (User)session.getAttribute("editor");
            newCommodity.setUserId(user.getId());
            newCommodity.setType("站内推荐");
            newCommodity.setState(1);
        }else{
            User user = (User)session.getAttribute("user");
            newCommodity.setUserId(user.getId());
            newCommodity.setType("用户分享");
        }


        newCommodity.setId(EntityIDFactory.createId());
        newCommodity.setShareTime(new Date());
        return  service.addCommodity(newCommodity);
    }


    @ResponseBody
    @RequestMapping(value = {"/center/ajax/updateCommodity","/admin/ajax/updateCommodity"},method = RequestMethod.POST)
    public Boolean updateCommodity(Commodity newCommodity){
        newCommodity.setState(0);
        return service.updateCommodity(newCommodity);
    }

    @ResponseBody
    @RequestMapping(value = {"/center/ajax/getDetail","/admin/ajax/getDetail"})
    public Map<String,Object> getDetail(String id){
        return service.getDetail(id);
    }

    /**
     * 个人中心删除优惠
     * @param id
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/center/ajax/deleteCommodity",method = RequestMethod.POST)
    public Boolean deleteCommodity(@RequestParam("id") String id,HttpSession session){
        Map<String,Object> cdy = service.getDetail(id);
        User user = (User)session.getAttribute("user");
        if(cdy==null){
            return false;
        }
        if(!user.getId().equals(cdy.get("userId"))){
            return false;
        }
        return  service.deleteCommodity(id);
    }

    /**
     * 下拉底部刷新
     * @param page
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/ajax/appendNextPage",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> appendNextPage(Integer page, HttpSession session){
        QueryPojo pojo = (QueryPojo) session.getAttribute("qurey");
        pojo.setOffset((page-1) * 5);
        return service.getDetailedList(pojo);
    }

    /**
     * 热门排行
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/ajax/addHotList",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> addHotList(){
        QueryPojo pojo = new QueryPojo();
        pojo.setOrder("commentNumber desc");
        pojo.setOffset(0);
        pojo.setRows(7);

        return service.getDetailedList(pojo);
    }


    /**
     * 个人中心获取自己发布过的优惠
     * @param session
     * @param page
     * @return
     */
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

    /**
     * 后台管理 优惠列表
     * @param state
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/admin/ajax/adminCommodityList",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public List<Map<String,Object>> adminCommodityList(Integer state){
        QueryPojo pojo = new QueryPojo();
        int[] states;
        if(state==-1 || state ==null){
            states = new int[]{0,1,2,3,4};
        }else{
            states = new int[]{state};
        }
        pojo.setStates(states);
        pojo.setOffset(0);
        pojo.setRows(-1);
        pojo.setOrder("a.share_time desc");
        return service.getDetailedList(pojo);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/AdminDeleteCommodity",method = RequestMethod.POST)
    public Boolean AdminDeleteCommodity(@RequestParam("id") String id){

        return  service.deleteCommodity(id);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/changeCommodityState",method = RequestMethod.POST)
    public Boolean changeCommodityState(@RequestParam("idsStr") String idsStr,
                                        @RequestParam("newState")int newState,
                                        HttpSession session){
        String[] ids ={};
        if(idsStr!=null){
            ids = idsStr.split(",");
            User user = (User)session.getAttribute("editor");
            service.updateCommodityState(ids,newState,user.getId());
        }
        return true;
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
