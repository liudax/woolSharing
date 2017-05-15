package com.shisheng.dao;

import com.shisheng.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int addUser(User user);

    User getUserByUserName(String loginName);

    int updateUser(User user);

    int updateState(@Param("id")String id,@Param("state")int state);

    int updatePassword(@Param("loginName")String loginName,@Param("password")String password);

    List<User> getUserByCondition(@Param("state") Integer state,@Param("property")Integer property);

    int updateProperty(@Param("id")String id,@Param("property")int property);
}