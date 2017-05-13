package com.shisheng.service.serviceImp;

import com.shisheng.dao.UserMapper;
import com.shisheng.entity.User;
import com.shisheng.service.IUserService;
import com.shisheng.util.EntityIDFactory;
import com.shisheng.util.MyBoolean;
import com.shisheng.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Magic on 2017/4/15.
 */

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper dao;


    /**
     * 注册
     * @param user
     * @return
     */
    public MyBoolean register(User user) {
        if(!checkLoginName(user.getLoginName())){
            return new MyBoolean(false,"用户名重复");
        }

        user.setRegisterTime(new Date());
        user.setId(EntityIDFactory.createId());
        int addCount = dao.addUser(user);
        if(addCount==1){
            return new MyBoolean(true,"注册成功");
        }else{
            return new MyBoolean(false,"注册失败");
        }
    }


    /**
     * 登陆验证
     * @param userName
     * @param password
     * @return
     */
    public MyResult<User> login(String userName, String password , HttpServletRequest request) {

        User user = dao.getUserByUserName(userName);
        if(user!=null && user.getPassword().equals(password)){
            request.getSession().setAttribute("user" , user);
            return new MyResult<User>(true,user);
        }else{
            return new MyResult<User>(false,"密码错误");
        }


    }

    public Boolean checkLoginName(String loginName) {
        return dao.getUserByUserName(loginName)==null?true:false;
    }

    public User updateUser(User user) {
        dao.updateUser(user);

        return dao.getUserByUserName(user.getLoginName());
    }

    public MyBoolean updatePassword(String loginName, String password, String newPassword) {

        User user = dao.getUserByUserName(loginName);
        if(user!=null && user.getPassword().equals(password)){
            dao.updatePassword(loginName,newPassword);
            return new MyBoolean(true,"");
        }else{
            return new MyBoolean(false,"原密码错误");
        }
    }
}
