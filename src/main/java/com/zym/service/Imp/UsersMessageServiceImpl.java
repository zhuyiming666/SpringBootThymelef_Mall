package com.zym.service.Imp;

import com.zym.mapper.UsersMessagesDao;
import com.zym.pojo.Address;
import com.zym.pojo.District;
import com.zym.pojo.UsersMessage;
import com.zym.service.UsersMessageService;
import com.zym.utils.MyString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsersMessageServiceImpl implements UsersMessageService {

    @Resource
    private UsersMessagesDao usersMessagesDao;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public Integer editMessage(UsersMessage usersMessage) {
        return usersMessagesDao.editMessage(usersMessage);
    }

    @Override
    public UsersMessage getUsersMessageById(Integer id) {
        return usersMessagesDao.getUsersMessageById(id);
    }

    @Override
    public Integer updateAvatarById(Integer id, String avatar) {
        return usersMessagesDao.updateAvatarById(id, avatar);
    }

    @Override
    public Integer updatePassword(String password, String newPassword, Integer id) {

        //判断用户输入的旧密码是否与数据库一致
        String dbpassword=usersMessagesDao.getUsersPasswordById(id);

        String salt = usersMessagesDao.getUsersSaltById(id);
        String mypassword = MyString.getMd5Password(password, salt);

        if (!mypassword.equals(dbpassword)) {
            System.out.println("原密码错误!");
            return -1;
        }

        //加密新的密码
        String newSalt = UUID.randomUUID().toString().toUpperCase();
        String md5NewPassword = MyString.getMd5Password(newPassword, newSalt);

        //更新对象中的密码和盐值
        Integer x = usersMessagesDao.updatePassword(newSalt, md5NewPassword, id);

        return x;
    }

    @Override
    public Integer addNewAddress(Integer uid, String username, Address address) {

        Integer count=usersMessagesDao.countByUid(uid);
        if(count>=maxCount){
            return -1;
        }

        //省市区
        String provinceName=usersMessagesDao.findNameByCode(address.getProvinceCode());
        String cityName=usersMessagesDao.findNameByCode(address.getCityCode());
        String areaName=usersMessagesDao.findNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        Integer isDefault=count==0?1:0;
        address.setIsDefault(isDefault);

        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows=usersMessagesDao.insertAddress(address);
        if(rows!=1){
            return -2;
        }
        return rows;
    }

    @Override
    public List<District> findByParent(String parent) {
        List<District> list=usersMessagesDao.findByParent(parent);
        /*
        1.在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null
        2.这样可以节省流量，提高程序的性能和效率
         */
        for (District district:list){
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String findNameByCode(String code) {
        return usersMessagesDao.findNameByCode(code);
    }

    @Override
    public List<Address> getAddressAll(Integer uId) {
        return usersMessagesDao.getAddressAll(uId);
    }

    @Override
    public Integer setDefaultAddress(Integer uId, Integer aId) {
        usersMessagesDao.updateNonDefault(uId);
        Integer x=usersMessagesDao.updateDefaultByAid(aId);
        return x;
    }

    @Override
    public Integer deleteAddress(Integer aId) {
        Integer x=usersMessagesDao.deleteAddress(aId);
        if (x==1){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Address getAddressByAid(Integer aid,Integer uid) {
        Address address= usersMessagesDao.getAddressByAid(aid);
        if(!address.getUid().equals(uid)){
            return null;
        }
        address.setAreaCode(null);
        address.setCityCode(null);
        address.setProvinceCode(null);
        return address;
    }
}
