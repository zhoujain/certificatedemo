package com.zhoujian.service.impl;


import com.zhoujian.dao.UserMapper;
import com.zhoujian.domain.User;
import com.zhoujian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryUserList();
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * 用户名和密码验证
     * @param username
     * @param upwd
     * @return
     */
    @Override
    public Boolean loginUser(String username, String upwd) {
        User user = userMapper.findUserByUsername(username);
        if(user!=null){
            String password = userMapper.findUpwdByUsername(username);
            if(upwd.equals(password)){
                return  true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Integer uTidByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        if(user!=null){
            return user.getUtid();
        }
        return 0;
    }

    /**
     * 用户名获取权限
     * @param username
     * @return
     */
    @Override
    public String uAccessByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        if(user!=null){
            return user.getUaccess();
        }
        return null;
    }
}
