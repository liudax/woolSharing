package com.shisheng.controller;

import com.shisheng.entity.Commodity;
import com.shisheng.entity.User;
import com.shisheng.service.IUserService;
import com.shisheng.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Magic on 2017/4/15.
 */
@Controller
public class UserController {

    @Resource(name="userService")
    IUserService service;


    @ResponseBody
    @RequestMapping(value = "/admin/ajax/addUser",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyBoolean addUser(User user){


        return  service.register(user);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/userList",produces = "application/json;charset=utf-8")
    public List<User> userList(HttpSession session){
        User user =(User)session.getAttribute("editor");
        if(user.getProperty()==2){
            return service.getUserByCondition(null,null);
        }else {
            return service.getUserByCondition(null,0);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/changeState",produces = "application/json;charset=utf-8")
    public Boolean changeState(Integer state,String id){

        return  service.updateState(id,state);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/ajax/changeProperty",produces = "application/json;charset=utf-8")
    public Boolean changeProperty(Integer property,String id,HttpSession session){
        User user =(User)session.getAttribute("editor");
        if(user.getProperty()==2){
            return service.updateProperty(id,property);
        }else {
            return false;
        }
    }



    @ResponseBody
    @RequestMapping(value = "/user/adminLogin",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyResult<User> adminLogin(@RequestParam("loginName")String loginName,
                                @RequestParam("password")String password,
                                HttpSession session){
        return service.adminLogin(loginName,password,session);
    }


    @ResponseBody
    @RequestMapping(value = "/admin/ajax/getAdmin",
            produces = "application/json;charset=utf-8")
    public User adminLogin(HttpSession session){
        return (User)session.getAttribute("editor");
    }



    @RequestMapping("/center/{page}")
    public String userPage(@PathVariable("page") String page,String id, Model model){
        String path = "user/"+page;
        if(page.equals("updateMsg")){model.addAttribute("updateId",id);}
        return path;
    }



    @RequestMapping(value = "/center/ajax/updateUser",method = RequestMethod.POST)
    public String updateUser(User user,HttpSession session){

        session.setAttribute("user",service.updateUser(user));
        return "redirect:/center/info";
    }


    @ResponseBody
    @RequestMapping(value = "/center/updatePassword",method = RequestMethod.POST)
    public MyBoolean updatePassword(@RequestParam("loginName") String loginName,
                                 @RequestParam("password")String password,
                                 @RequestParam("newPassword")String newPassword){
        return service.updatePassword(loginName,password,newPassword);
    }



    @ResponseBody
    @RequestMapping(value = "/user/login",
            produces = "application/json;charset=utf-8")
    public MyResult<User> login(@RequestParam("loginName")String loginName,
                                @RequestParam("password")String password,
                                //@RequestParam("code")String code,
                                HttpServletRequest request){
        HttpSession session = request.getSession(true);
        if(!MyValidation.isValid(loginName)){
            return new MyResult<User>(false,"用户名为空");
        }
        if(!MyValidation.isValid(password)){
            return new MyResult<User>(false,"密码为空");
        }
        return service.login(loginName,password,session);
    }

    @ResponseBody
    @RequestMapping(value = "/user/isLogin",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyResult<User> isLogin(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user==null){
            return new MyResult<User>(false,"没有登陆");
        }
        return new MyResult<User>(true,user);
    }

    @ResponseBody
    @RequestMapping(value = "/user/cancel",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyResult<User> cancel(HttpSession session){
        session.setAttribute("user",null);
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/user/register",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyBoolean register(HttpSession session , User user , String code){
        String randomString = (String)session.getAttribute("randomString");
        if(randomString.toUpperCase().equals(code)){
            return service.register(user);
        }else{
            return new MyBoolean(false,"验证码错误");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/user/checkLoginName",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public Boolean checkLoginName(String loginName){
       return service.checkLoginName(loginName);
    }





    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        CaptchaUtil.outputCaptcha(request, response);

    }

}
