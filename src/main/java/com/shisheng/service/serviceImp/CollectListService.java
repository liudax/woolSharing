package com.shisheng.service.serviceImp;

import com.shisheng.dao.CollectionListMapper;
import com.shisheng.entity.CollectionList;
import com.shisheng.service.ICollectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/5/14.
 */

@Service
public class CollectListService implements ICollectListService {

    @Autowired
    CollectionListMapper dao;


    public Boolean addCollect(CollectionList collection) {
        return dao.addCollect(collection)==1?true:false;
    }

    public List<Map<String, Object>> getCollectList(String userId) {
        return dao.getCollectList(userId);
    }

    public Boolean deleteCollect(String commodityId, String userId) {
        return dao.deleteCollect(commodityId,userId)==1?true:false;
    }
}
