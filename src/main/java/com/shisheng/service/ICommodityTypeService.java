package com.shisheng.service;

import com.shisheng.entity.ChildCommodityType;
import com.shisheng.entity.ParentCommodityType;
import com.shisheng.util.MyResult;

import java.util.List;

/**
 * Created by Magic on 2017/4/18.
 */
public interface ICommodityTypeService {

    int addParentType(ParentCommodityType parentCommodityType);

    int addChildCommodityType(ChildCommodityType childCommodityType);

    MyResult<List<ParentCommodityType>> getAllType();

}
