package com.shisheng.controller;

import com.shisheng.entity.HotW;
import com.shisheng.entity.Platform;
import com.shisheng.service.ICommodityService;
import com.shisheng.service.IHotWordsService;
import com.shisheng.service.IPlatformService;
import com.shisheng.util.PictureOperation;
import com.shisheng.util.QueryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/25.
 */

@Controller
public class PageController {

    @Autowired
    IPlatformService platformService;

    @Autowired
    ICommodityService commodityService;

    @Autowired
    IHotWordsService hotWordsService;


    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index","/index.jj"},method = RequestMethod.GET)
    public String index(Model model){
        //获取信息列表
        QueryPojo qurey = new QueryPojo();
        List<Map<String,Object>> cdyList = commodityService.getDetailedList(qurey);

        //获取商城列表
        List<Platform> plList =platformService.getPlatformList();

        //获取热门搜索词
        List<HotW> hotWordsList =  hotWordsService.getAll();
        model.addAttribute("cdyList",cdyList);
        model.addAttribute("plList",plList);
        model.addAttribute("hotWordsList",hotWordsList);
        return "index";
    }

    /**
     * 站内推荐，用户分享
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {"/zn","yh"},method = RequestMethod.GET)
    public String shareTypeList(Model model, HttpServletRequest request){
        String shareType = "/zn".equals(request.getServletPath())?"站内推荐":"用户分享";
        QueryPojo pojo = new QueryPojo();
        pojo.setShareType(shareType);

        List<Map<String,Object>> list = commodityService.getDetailedList(pojo);
        model.addAttribute("cdyList",list);
        return "list";
    }

    /**
     * 搜索
     * @param search
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String searchList(@RequestParam(required = false)String search , Model model, HttpSession session){

        QueryPojo pojo = new QueryPojo();
        pojo.setSearch(search);

        //存入搜索词汇
        try{
            hotWordsService.addNewWords(search);
        }catch (Exception e){

        }
        List<Map<String,Object>> list = commodityService.getDetailedList(pojo);
        model.addAttribute("cdyList",list);
        model.addAttribute("search",search);

        return "list";
    }


    /**
     * 按商品种类显示列表
     * @param typeId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/type/{typeId}",method = RequestMethod.GET)
    public String typeList(@PathVariable("typeId")String typeId, Model model, HttpSession session){

        QueryPojo pojo = new QueryPojo();
        pojo.setTypeId(typeId);
        List<Map<String,Object>> list = commodityService.getDetailedList(pojo);
        model.addAttribute("cdyList",list);
        return "list";
    }


    /**
     * 优惠信息详情页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/detail",method = RequestMethod.GET)
    public String cdyDetail(@PathVariable("id") String id , Model model){
        model.addAttribute("cdy",commodityService.getDetail(id));

        return "detail";
    }


    /**
     * 商城列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/mall",method = RequestMethod.GET)
    public String mallList(Model model){
        List<Platform> list = platformService.getPlatformList();
        model.addAttribute("mallList",list);
        return "mall";
    }

    /**
     * 商城详情页，含该商城的优惠信息列表
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/mall/{id}",method = RequestMethod.GET)
    public String getMallDetail(@PathVariable("id") String id , Integer page ,Model model){

        Platform pl = platformService.getPlatformDetail(id);

        QueryPojo pojo = new QueryPojo();
        if(page!=null){
            pojo.setOffset((page-1) * 15);
        }
        pojo.setRows(15);
        pojo.setPatformId(id);

        List<Map<String,Object>> cdyList =  commodityService.getDetailedList(pojo);
        model.addAttribute("pl",pl);
        model.addAttribute("cdyList",cdyList);

        return "mallDetail";
    }



    @RequestMapping(value = "/{addr}/image", method = RequestMethod.GET)
    public void getImage(@PathVariable("addr") String addr,
                         HttpServletResponse response) {
        String fileType = addr.substring(addr.lastIndexOf(".") + 1, addr.length());
        response.setContentType("image/" + fileType);
        ServletOutputStream sos = null;
        FileInputStream fis = null;
        File file = new File("E:\\CommodityPicture\\" + addr);
        if (!file.exists()) {
            file = new File("E:\\CommodityPicture\\图片1.png");
        }
        try {
            fis = new FileInputStream(file);
            sos = response.getOutputStream();
            byte[] b = new byte[fis.available()];
            fis.read(b);
            sos.write(b);
            sos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {

                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {

                }
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = {"/center/uploadImage","/admin/uploadImage"},method = RequestMethod.POST)
    public String uploadImage(@RequestParam(value ="image")MultipartFile image){
        String name = image.getName();
        return PictureOperation.upload(image);
    }


}
