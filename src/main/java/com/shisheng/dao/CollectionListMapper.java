package com.shisheng.dao;

import com.shisheng.entity.CollectionList;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/5/14.
 */
public interface CollectionListMapper {

    int addCollect(CollectionList collection);

    List<Map<String,Object>> getCollectList(String userId);

    int deleteCollect(@Param("commodityId")String commodityId,@Param("userId")String userId);
}
