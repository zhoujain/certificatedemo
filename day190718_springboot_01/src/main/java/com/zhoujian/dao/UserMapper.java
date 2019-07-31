package com.zhoujian.dao;

import com.zhoujian.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;

import java.util.List;



@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     * @return
     */
    List<User> queryUserList();

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    List<User> findUserByLikeUsername(String username);

    /**
     * 添加用户
     */
    Boolean insertUser(User user);

    /**
     * 删除用户
     */
    Boolean deleteUser(Integer uid);

    /**
     * 根据uid查询用户
     * @param uid
     * @return
     */
    User findUserByUid(Integer uid);


    /**
     * 根据username查询密码
     */
    String findUpwdByUsername(String username);

    Integer findUidByUsername(String username);
    /**
     * 修改用户
     */
    Boolean updateUser(User user);



}


