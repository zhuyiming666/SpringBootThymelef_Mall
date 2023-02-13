package com.zym.mapper;

import com.zym.pojo.Address;
import com.zym.pojo.District;
import com.zym.pojo.UsersMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsersMessageTest {

    @Resource
    private UsersMessagesDao usersMessagesDao;

    @Test
    public void test1(){
       Integer x=usersMessagesDao.updateAvatarById(2,"yimingLogo.jpg");
        System.out.println("x="+x);
    }

    @Test
    public void test2(){
        List<District> list=usersMessagesDao.findByParent("210100");
        for (District district:list){
            System.out.println(district);
        }
    }

    @Test
    public void test03(){
        String name=usersMessagesDao.findNameByCode("430000");
        System.out.println(name);
    }

    @Test
    public void test04(){
        List<Address> list=usersMessagesDao.getAddressAll(2);
        System.out.println(Arrays.asList(list));
    }
}
