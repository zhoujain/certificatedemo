package com.zhoujian.service;

import com.zhoujian.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {
    /**
     * 根据用户名，查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
