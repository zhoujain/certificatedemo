package com.zhoujian.service;

import com.zhoujian.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> queryAllUser();
    /**
     * 根据用户名，查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    Boolean loginUser(String username,String upwd);

    Integer uTidByUsername(String username);

    String uAccessByUsername(String username);

    String usernameByUid(Integer uid);
}
