package com.shisheng.dao;

import com.shisheng.entity.Platform;
import com.shisheng.util.EntityIDFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Magic on 2017/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PlatformMapperTest {

    @Autowired
    PlatformMapper dao;


    @Test
    public void addPlatform() throws Exception {

        String link = "http://www.tmall.com/";
        String name = "天猫商城";
        String intrduce = "天猫原名“淘宝商城”，是一个综合性购物网站，整合数千家品牌商、生产商，为商家和消费者之间提供一站式解决方案，提供100%品质保证的商品，7天无理由退货的售后服务，以及购物积分返\n";

        Platform p =  new Platform();
        p.setId(EntityIDFactory.createId());
        p.setPlatformName(name);
        p.setIntroduce(intrduce);
        p.setLink(link);
        dao.addPlatform(p);
    }

}