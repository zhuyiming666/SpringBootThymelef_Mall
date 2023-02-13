package com.zym.mapper;

import com.zym.pojo.ProductType;
import com.zym.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

@SpringBootTest //表示当前类是一个测试类，不会随同项目一同打包
@RunWith(SpringRunner.class)  //启动这个单元测试类(com.zym最外层可以不写)
public class UsersDaoTest {

    @Resource
    private UsersDao usersDao;



    @Test
    public void reg(){
     /*  Users users=new Users("1736810693@123.com","123456","000000",1);
       Integer x=usersDao.insertUsers(users);
       System.out.println("======="+x+"========");*/
      /*  Users users=usersDao.findByEmail("1736810693@qq.com");
        System.out.println(users);*/
    }

    @Test
    public void test1(){
        try {
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2() throws Exception{
        int x=9/0;
    }

}
