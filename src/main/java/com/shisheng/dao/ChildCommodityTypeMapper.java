package com.shisheng.dao;

import com.shisheng.entity.ChildCommodityType;

import java.util.List;

public interface ChildCommodityTypeMapper {

    int addChildType(ChildCommodityType childCommodityType);

    List<ChildCommodityType>  getChildTypeByParentId(String parentId);


}