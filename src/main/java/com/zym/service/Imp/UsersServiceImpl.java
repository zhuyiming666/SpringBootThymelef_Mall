package com.zym.service.Imp;

import com.zym.mapper.UsersDao;
import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import com.zym.service.UsersService;
import com.zym.service.ex.EmailExitsException;
import com.zym.service.ex.ServiceException;
import com.zym.service.ex.UpdateException;
import com.zym.utils.MyString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

@Transactional
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersDao usersDao;

    @Override
    public void reg(Users users){

        String oldPassword=users.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        users.setSalt(salt);

        String md5Password= MyString.getMd5Password(oldPassword,salt);
        users.setPassword(md5Password);
        users.setIsDelete(1);

        usersDao.insertUsers(users);
    }

    @Override
    public Users findUsersByEmail(String email) {
        return usersDao.findByEmail(email);
    }



    @Override
    public Users loginUsers(String email, String password) {
        Users users=usersDao.findByEmail(email);
        if(users==null){
            return null;
        }

        String oldPassword=users.getPassword();

        String salt=users.getSalt();


        String myPassword=MyString.getMd5Password(password,salt);


        if(myPassword.equals(oldPassword)){
            return users;
        }
        return null;
    }

    @Override
    public Integer insertUsersMessageId(Integer id) {
        return usersDao.insertUsersMessageId(id);
    }

    @Override
    public UsersMessage getUsersMessageById(Integer id) {
        return usersDao.getUsersMessageById(id);
    }


}
