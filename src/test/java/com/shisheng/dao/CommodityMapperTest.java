package com.shisheng.dao;

import com.shisheng.entity.Commodity;
import com.shisheng.util.EntityIDFactory;
import org.slf4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Magic on 2017/4/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CommodityMapperTest {
    @Autowired
    CommodityMapper dao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @Test
    public void addCommodity() throws Exception {

        /**
         * 20170418120026856 京东
         * 20170418120222826 天猫商城
         */
        Commodity c = new Commodity();
        c.setId(EntityIDFactory.createId());
        c.setTitle("测试标题");
        c.setPlatformId("平台ID");
        c.setLink("WWW.BAIDU.COM");
        c.setLabel("大气 上档次");
        c.setPricePoint("卖点");
        c.setUserId("pipixia");
        c.setReason("推荐理由");
        c.setType("分享分类");
        c.setChildTypeId("子分类ID");
        c.setParentTypeId("主分类ID");
        c.setImageAddr("图片地址");
        c.setShareTime(new Date());
        dao.addCommodity(c);
        /**
         * Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '" id", title, price_point,
         state, type, platform_id,
         link, editor_id' at line 1
         ; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '" id", title, price_point,
         state, type, platform_id,
         link, editor_id' at line 1

         */
    }
    @Test
    public void updateCommodity() throws Exception{
        String[] ids = new String[]{"20170516231510097","20170516231558247"};
        int count = dao.updateCommodityState(ids,1);
        System.out.println(count);
    }


}