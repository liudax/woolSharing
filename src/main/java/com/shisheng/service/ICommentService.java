package com.shisheng.service;

import com.shisheng.entity.Comment;
import com.shisheng.util.MyResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/24.
 */
public interface ICommentService {

    List<Comment> getCommentByCommodityId(String id);

    Comment addNewComment(Comment comment);

    String support(String id);

    List<Map<String,Object>> getCommentByUserId(String userId,int offset);

    Boolean  deleteComment(String id, String userId);
}
