package com.zym.service;

import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import com.zym.service.ex.ServiceException;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsersService {

    void reg(Users users);
    Users findUsersByEmail(String email);
    Users loginUsers(String email,String password);
    Integer insertUsersMessageId(Integer id);
    UsersMessage getUsersMessageById(Integer id);
}
