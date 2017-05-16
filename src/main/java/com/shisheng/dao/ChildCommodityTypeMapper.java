package com.shisheng.dao;

import com.shisheng.entity.ChildCommodityType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ChildCommodityTypeMapper {

    int addChildType(ChildCommodityType childCommodityType);

    List<ChildCommodityType>  getChildTypeByParentId(String parentId);

    List<Map<String,Object>> getChildList(String parentId);

    int updateChildTypeName(@Param("id") String id, @Param("newTypeName") String newTypeName);

    int deleteChildType(String id);

}