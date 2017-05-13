package com.shisheng.dao;

import com.shisheng.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int addUser(User user);

    User getUserByUserName(String loginName);

    int updateUser(User user);

    int updatePassword(@Param("loginName")String loginName,@Param("password")String password);
}