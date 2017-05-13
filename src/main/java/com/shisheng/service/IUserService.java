package com.shisheng.service;

import com.shisheng.entity.User;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.MyResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Magic on 2017/4/15.
 */
public interface IUserService {


     MyBoolean register(User user);



     MyResult<User> login(String userName, String password , HttpServletRequest request);


     Boolean checkLoginName(String loginName);

     User updateUser(User user);

     MyBoolean updatePassword(String loginName ,String password, String newPassword);
}
