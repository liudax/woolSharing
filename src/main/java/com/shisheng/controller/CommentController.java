package com.shisheng.controller;

import com.shisheng.entity.Comment;
import com.shisheng.entity.User;
import com.shisheng.service.ICommentService;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Magic on 2017/4/24.
 */
@Controller
public class CommentController {

    @Autowired
    ICommentService service;

    @ResponseBody
    @RequestMapping(value = "/ajax/allComments",
            produces = "application/json",
            method = RequestMethod.GET)
    public List<Comment> getCommentsByCommodityId(String commodityId){
        List<Comment> list= service.getCommentByCommodityId(commodityId);
        return list;
    }


    @ResponseBody
    @RequestMapping(value = "/ajax/addNewComment",
            produces = "application/json",
            method = RequestMethod.POST)
    public MyResult<Comment> addNewComment(Comment comment, HttpSession session){
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        if(user==null){
            return new MyResult<Comment>(false,"未登录");
        }
        comment.setUserId(user.getId());
        try{
            return new MyResult<Comment>(true,service.addNewComment(comment));
        }catch (Exception e){
            return new MyResult<Comment>(false,"评论失败");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/ajax/support",
            produces = "application/json",
            method = RequestMethod.POST)
    public String addNewComment(String id){
        service.support(id);
        return "true";
    }



}
