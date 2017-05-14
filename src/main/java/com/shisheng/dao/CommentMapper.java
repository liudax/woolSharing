package com.shisheng.dao;

import com.shisheng.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    List<Comment> getCommentsByCommodityId(String commodityId);

    int deleteById(String id);

    int deleteByCommodityId(String commodityId);

    int insert(Comment record);

    Comment getCommentById(String id);

    int updateCommentSupportNum(String id);

    List<Map<String,Object>> getCommentByUserId(@Param("userId") String userId,
                                                @Param("offset") int offset);



}