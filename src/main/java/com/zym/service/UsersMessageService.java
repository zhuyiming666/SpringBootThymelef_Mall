package com.zym.service;

import com.zym.pojo.Address;
import com.zym.pojo.District;
import com.zym.pojo.UsersMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMessageService {

    Integer editMessage(UsersMessage usersMessage);

    UsersMessage getUsersMessageById(Integer id);  //获取用户详细信息

    /**
     * 修改用户的头像
     * @param id     用户的id
     * @param avatar 用户头像的路径
     * @return
     */
    Integer updateAvatarById(Integer id, String avatar);

    Integer updatePassword(String password,String newPassword,Integer id);

    Integer addNewAddress(Integer uid,String username,Address address);

    List<District> findByParent(String parent);

    String findNameByCode(String code);

    List<Address> getAddressAll(Integer uId);

    Integer setDefaultAddress(Integer uId,Integer aId);

    Integer deleteAddress(Integer aId);

    Address getAddressByAid(Integer aid,Integer uid);
}
