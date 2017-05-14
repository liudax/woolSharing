package com.shisheng.service.serviceImp;

import com.shisheng.dao.CommentMapper;
import com.shisheng.dao.CommodityMapper;
import com.shisheng.entity.Comment;
import com.shisheng.entity.Commodity;
import com.shisheng.entity.User;
import com.shisheng.service.ICommodityService;
import com.shisheng.util.EntityIDFactory;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.QueryPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/4/17.
 */

@Service
public class  CommodityService implements ICommodityService {


    @Autowired
    CommodityMapper dao;

    @Autowired
    CommentMapper commentMapper;


    public Boolean addCommodity(Commodity commodity) {

        return dao.addCommodity(commodity)==1?true:false;
    }

    public List<Map<String, Object>> getDetailedList(QueryPojo query) {

        return dao.getDetailedList(query);
    }


    public Map<String, Object> getDetail(String id) {

        return dao.getCommodityDetail(id);
    }

    @Transactional
    public Boolean deleteCommodity(String id) {
        int count = dao.deleteCommodity(id);
        commentMapper.deleteByCommodityId(id);
        return count==1?true:false;
    }

}
