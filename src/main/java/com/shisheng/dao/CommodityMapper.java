package com.shisheng.dao;

import com.shisheng.entity.Commodity;
import com.shisheng.util.QueryPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityMapper {

    //添加一个商品信息
    int addCommodity(Commodity commodity);

    int updateCommodityState(@Param("ids") String[] ids,
                             @Param("newState")int newState,
                             @Param("editorId")String editorId);

    //根据条件查询商品明细列表
    List<Map<String,Object>> getDetailedList(QueryPojo query);

    //根据id查询商品明细
    Map<String,Object> getCommodityDetail(String id);

    int deleteCommodity(String id);

}