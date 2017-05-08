package com.sota.service;

import com.sota.entity.User;
import com.sota.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2017/2/15.
 * User业务逻辑
 */
@Service
public class UserService {
    @Autowired
    private UserRepositoty userRepositoty;

    public User findUserByName(String name){
        User user = null;
        try{
            user = userRepositoty.findByUserName(name);
        }catch (Exception e){}
        return user;
    }

    public User findByUserID(int id) {
        User user = null;
        try{
            user = userRepositoty.findByUserID(id);
        }catch (Exception e){}
        return user;
    }
}
