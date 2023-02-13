package com.zym.mapper;

import com.zym.pojo.Address;
import com.zym.pojo.District;
import com.zym.pojo.Users;
import com.zym.pojo.UsersMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMessagesDao {

    Integer editMessage(UsersMessage usersMessage);
    UsersMessage getUsersMessageById(@Param("id") Integer id);  //获取用户详细信息
    Integer updateAvatarById(@Param("id") Integer id,@Param("avatar") String avatar);
    Integer updatePassword(@Param("newSalt") String newSalt, @Param("newPassword")String newPassword,@Param("id") Integer id);
/*
    UsersMessage getUsersMessagePassword(@Param("id") Integer id,)
*/
    String getUsersSaltById(@Param("id") Integer id);
    String getUsersPasswordById(@Param("id") Integer id);

    Integer insertAddress(Address address);
    Integer countByUid(@Param("id")Integer id);  //获取收获地址有几条

    /**
     * 根据父代号查询区域信息
     * @param parent  父代号
     * @return    某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);

    List<Address> getAddressAll(@Param("uId") Integer uId);  //获取所有收获地址

    /**
     * 将用户所有的收获地址设置为非默认的
     * @param uId  用户uId
     * @return
     */
    Integer updateNonDefault(@Param("uId") Integer uId);

    /**
     * 将用户指定的收获地址设置为默认的
     * @param aId  用户的收获地址aId
     * @return
     */
    Integer updateDefaultByAid(@Param("aId") Integer aId);

    /**
     * 删除某个收获地址
     * @param aId
     * @return
     */
    Integer deleteAddress(@Param("aId")Integer aId);

    Address getAddressByAid(Integer aid);


}
