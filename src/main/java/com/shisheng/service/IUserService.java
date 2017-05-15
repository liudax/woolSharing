package com.shisheng.service;

import com.shisheng.entity.User;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.MyResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Magic on 2017/4/15.
 */
public interface IUserService {


     MyBoolean register(User user);

     List<User> getUserByCondition(Integer state,Integer property);

     MyResult<User> login(String userName, String password , HttpSession session);

     MyResult<User> adminLogin(String userName, String password , HttpSession session);

     Boolean checkLoginName(String loginName);

     User updateUser(User user);

     MyBoolean updatePassword(String loginName ,String password, String newPassword);

     Boolean updateState(String id ,int state);

     Boolean updateProperty(String id ,int state);

}
