package com.shisheng.dao;

import com.shisheng.entity.CollectionList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Magic on 2017/5/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CollectionListMapperTest {

    @Autowired
    CollectionListMapper dao;

    @Test
    public void addCollection(){
        CollectionList collectionList = new CollectionList();
        collectionList.setCollectTime(new Date());
        collectionList.setUserId("1111");
        collectionList.setCommodityId("222");
        dao.addCollect(collectionList);
    }

}