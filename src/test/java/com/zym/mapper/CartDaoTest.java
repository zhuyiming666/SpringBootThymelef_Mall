package com.zym.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartDaoTest {

    @Resource
    private CartDao cartDao;

    @Test
    public void test01(){
        HashMap<String,Object> map=cartDao.findCartNumber(2,4);
        System.out.println(map);
    }
}
