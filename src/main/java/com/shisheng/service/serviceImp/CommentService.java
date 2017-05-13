package com.shisheng.service.serviceImp;

import com.shisheng.dao.CommentMapper;
import com.shisheng.entity.Comment;
import com.shisheng.entity.User;
import com.shisheng.service.ICommentService;
import com.shisheng.util.EntityIDFactory;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.event.ObjectChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2017/4/24.
 */

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentMapper dao;
    public List<Comment> getCommentByCommodityId(String id) {
        List<Comment> list = dao.getCommentsByCommodityId(id);
        for(Comment c : list){

            Comment cur = c;
            String pId;
            while((pId=cur.getAnswerId())!=null){
                Comment temp = dao.getCommentById(pId);
                cur.setAnswer(temp);
                cur = temp;
            }
        }
        return list;
    }

    public Comment addNewComment(Comment comment) {
        comment.setId(EntityIDFactory.createId());
        comment.setCommentTime(new Date());
        int count = dao.insert(comment);
        Comment c =dao.getCommentById(comment.getId());
        Comment cur = c;
        String pId;
        while((pId=cur.getAnswerId())!=null){
            Comment temp = dao.getCommentById(pId);
            cur.setAnswer(temp);
            cur = temp;
        }
        return c;
    }

    public String support(String id) {


        try{
            dao.updateCommentSupportNum(id);
        }catch (Exception e){

        }
        return "true";
    }


}
