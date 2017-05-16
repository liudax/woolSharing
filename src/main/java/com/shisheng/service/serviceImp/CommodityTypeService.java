package com.shisheng.service.serviceImp;

import com.shisheng.dao.ChildCommodityTypeMapper;
import com.shisheng.dao.ParentCommodityTypeMapper;
import com.shisheng.entity.ChildCommodityType;
import com.shisheng.entity.ParentCommodityType;
import com.shisheng.service.ICommodityTypeService;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/18.
 */


@Service
public class CommodityTypeService implements ICommodityTypeService{

    @Autowired
    ParentCommodityTypeMapper parentDao;

    @Autowired
    ChildCommodityTypeMapper childDao;
    public int addParentType(ParentCommodityType parentCommodityType) {
        return parentDao.addParentType(parentCommodityType);
    }

    public int addChildType(ChildCommodityType childCommodityType) {
        return childDao.addChildType(childCommodityType);
    }

    public int updateChildTypeName(String id, String newTypeName) {
        return childDao.updateChildTypeName(id,newTypeName);
    }

    public int updateParentTypeName(String id, String newTypeName) {
        return parentDao.updateParentTypeName(id,newTypeName);
    }

    public int deleteChildType(String id) {
        return childDao.deleteChildType(id);
    }

    public int deleteParentType(String id) {
        return parentDao.deleteParentType(id);
    }

    public MyResult<List<ParentCommodityType>> getAllType() {
        List<ParentCommodityType> pType = parentDao.getParentCommodityTypeList();

        for(ParentCommodityType p : pType){
            List<ChildCommodityType> cType = childDao.getChildTypeByParentId(p.getId());
            p.setChildren(cType);
        }

        return new MyResult<List<ParentCommodityType>>(true,pType);
    }

    public List<Map<String, Object>> getParentList() {

        return parentDao.getParentList();
    }

    public List<Map<String, Object>> getChildList(String parentId) {
        return childDao.getChildList(parentId);
    }
}
