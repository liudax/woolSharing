package com.shisheng.service;

import com.shisheng.entity.CollectionList;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/5/14.
 */
public interface ICollectListService {


    Boolean addCollect(CollectionList collection);

    List<Map<String,Object>> getCollectList(String userId);

    Boolean deleteCollect(String commodityId,String userId);
}
