package com.shisheng.service;

import com.shisheng.entity.ChildCommodityType;
import com.shisheng.entity.ParentCommodityType;
import com.shisheng.util.MyResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/18.
 */
public interface ICommodityTypeService {

    int addParentType(ParentCommodityType parentCommodityType);

    int addChildType(ChildCommodityType childCommodityType);

    int updateChildTypeName(String id,String newTypeName);

    int updateParentTypeName(String id,String newTypeName);

    int deleteChildType(String id);

    int deleteParentType(String id);

    MyResult<List<ParentCommodityType>> getAllType();

    List<Map<String,Object>> getParentList();

    List<Map<String,Object>> getChildList(String parentId);

}
