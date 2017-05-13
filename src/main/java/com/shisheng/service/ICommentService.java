package com.shisheng.service;

import com.shisheng.entity.Comment;
import com.shisheng.util.MyResult;

import java.util.List;

/**
 * Created by Magic on 2017/4/24.
 */
public interface ICommentService {

    List<Comment> getCommentByCommodityId(String id);

    Comment addNewComment(Comment comment);

    String support(String id);
}
