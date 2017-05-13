package com.shisheng.dao;

import com.shisheng.entity.Comment;

import java.util.List;

public interface CommentMapper {

    List<Comment> getCommentsByCommodityId(String commodityId);

    int deleteById(String id);

    int insert(Comment record);

    Comment getCommentById(String id);

    int updateCommentSupportNum(String id);



}