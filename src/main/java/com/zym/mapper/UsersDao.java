package com.zym.mapper;

import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {

    Integer insertUsers(Users users);
    Users findByEmail(@Param("email") String email);
    Users loginUsers(@Param("email")String email,@Param("password")String password);
    Integer insertUsersMessageId(Integer id); //插入UsersMessage Id
    UsersMessage getUsersMessageById(@Param("id") Integer id);  //获取用户详细信息
}
