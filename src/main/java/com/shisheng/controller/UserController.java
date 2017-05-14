package com.shisheng.controller;

import com.shisheng.entity.User;
import com.shisheng.service.IUserService;
import com.shisheng.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Magic on 2017/4/15.
 */
@Controller
public class UserController {

    @Resource(name="userService")
    IUserService service;

    /*
    @RequestMapping("/center/info")
    public String info(){
        return "user/info";
    }

    @RequestMapping("/center/change_info")
    public String change_info(){return "user/change_info";}

    @RequestMapping("/center/change_password")
    public String change_password(){return "user/change_password";}*/

    @RequestMapping("/center/{page}")
    public String userPage(@PathVariable("page") String page){
        String path = "user/"+page;
        //System.out.println(path);
        return path;
    }


   /* @RequestMapping(value = "/center/updatePassword",method = RequestMethod.POST)
    public String updatePassword(User user){


        return "redirect:/center/info";
    }
    */

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
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public MyResult<User> login(@RequestParam("loginName")String loginName,
                                @RequestParam("password")String password,
                                //@RequestParam("code")String code,
                                HttpServletRequest request){
        HttpSession session = request.getSession(true);
        /*String randomString = (String)session.getAttribute("randomString");
        if(!randomString.toUpperCase().equals(code)){
            return new MyResult<User>(false,"验证码错误");
        }*/
        if(!MyValidation.isValid(loginName)){
            return new MyResult<User>(false,"用户名为空");
        }
        if(!MyValidation.isValid(password)){
            return new MyResult<User>(false,"密码为空");
        }
        return service.login(loginName,password,request);
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
