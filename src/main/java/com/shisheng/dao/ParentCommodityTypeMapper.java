package com.shisheng.dao;


import com.shisheng.entity.ParentCommodityType;

import java.util.List;

public interface ParentCommodityTypeMapper {


    int addParentType(ParentCommodityType parentCommodityType);

    List<ParentCommodityType> getParentCommodityTypeList();


}