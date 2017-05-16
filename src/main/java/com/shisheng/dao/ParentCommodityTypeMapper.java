package com.shisheng.dao;


import com.shisheng.entity.ParentCommodityType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ParentCommodityTypeMapper {


    int addParentType(ParentCommodityType parentCommodityType);

    List<ParentCommodityType> getParentCommodityTypeList();

    List<Map<String,Object>> getParentList();

    int updateParentTypeName(@Param("id") String id, @Param("newTypeName") String newTypeName);

    int deleteParentType(String id);
}