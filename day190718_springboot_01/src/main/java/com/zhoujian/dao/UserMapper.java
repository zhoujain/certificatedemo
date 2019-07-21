package com.zhoujian.dao;

import com.zhoujian.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryUserList();

    User findUserByUsername(String username);
}


