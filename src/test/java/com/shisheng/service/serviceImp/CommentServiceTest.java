package com.shisheng.service.serviceImp;

import com.shisheng.dao.CommentMapper;
import com.shisheng.entity.Comment;
import com.shisheng.service.ICommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Magic on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class CommentServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ICommentService service;

    @Autowired
    CommentMapper dao;
    @Test
    public void getCommentByCommodityId() throws Exception {
        /*List<Comment> list = service.getCommentByCommodityId("111");
        logger.info("list={}",list);*/
        System.out.println(dao.getCommentById("01").getUserName());
        /**
         * list=[Comment{id='01', userId='null', commodityId='111', answerId='null', supportNum='null', content='测试1', answer=null},
         * Comment{id='02', userId='null', commodityId='111', answerId='01', supportNum='null', content='测试2',
         *              answer=Comment{id='01', userId='null', commodityId='111', answerId='null', supportNum='null', content='测试1', answer=null}},
         * Comment{id='03', userId='null', commodityId='111', answerId='02', supportNum='null', content='测试3', answer=Comment{id='02', userId='null', commodityId='111', answerId='01', supportNum='null', content='测试2', answer=Comment{id='01', userId='null', commodityId='111', answerId='null', supportNum='null', content='测试1', answer=null}}},
         * Comment{id='04', userId='null', commodityId='111', answerId='03', supportNum='null', content='测试4', answer=Comment{id='03', userId='null', commodityId='111', answerId='02', supportNum='null', content='测试3', answer=Comment{id='02', userId='null', commodityId='111', answerId='01', supportNum='null', content='测试2', answer=Comment{id='01', userId='null', commodityId='111', answerId='null', supportNum='null', content='测试1', answer=null}}}},
         * Comment{id='05', userId='null', commodityId='111', answerId='04', supportNum='null', content='测试5', answer=Comment{id='04', userId='null', commodityId='111', answerId='03', supportNum='null', content='测试4', answer=Comment{id='03', userId='null', commodityId='111', answerId='02', supportNum='null', content='测试3', answer=Comment{id='02', userId='null', commodityId='111', answerId='01', supportNum='null', content='测试2', answer=Comment{id='01', userId='null', commodityId='111', answerId='null', supportNum='null', content='测试1', answer=null}}}}}]

         */

    }

}