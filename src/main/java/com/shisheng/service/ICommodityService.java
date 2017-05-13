package com.shisheng.service;

import com.shisheng.entity.Commodity;
import com.shisheng.util.QueryPojo;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/17.
 */
public interface ICommodityService {

     Boolean addCommodity(Commodity commodity);

     List<Map<String,Object>> getDetailedList(QueryPojo query);

     Map<String,Object> getDetail(String id);


}