package com.shisheng.service.serviceImp;

import com.shisheng.entity.User;
import com.shisheng.service.IUserService;
import com.shisheng.util.MyResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Magic on 2017/4/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class UserServiceTest {

    @Autowired
    IUserService service;
    @Test
    public void register() throws Exception {

    }

}