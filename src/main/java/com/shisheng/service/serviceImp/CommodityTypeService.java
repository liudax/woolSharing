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
        return 0;
    }

    public int addChildCommodityType(ChildCommodityType childCommodityType) {
        return 0;
    }

    public MyResult<List<ParentCommodityType>> getAllType() {
        List<ParentCommodityType> pType = parentDao.getParentCommodityTypeList();

        for(ParentCommodityType p : pType){
            List<ChildCommodityType> cType = childDao.getChildTypeByParentId(p.getId());
            p.setChildren(cType);
        }

        return new MyResult<List<ParentCommodityType>>(true,pType);
    }
}
