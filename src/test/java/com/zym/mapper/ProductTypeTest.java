package com.zym.mapper;

import com.zym.pojo.ProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTypeTest {

    @Resource
    ProductDao productDao;

    @Test
    public void test1(){
        List<ProductType> list =productDao.getProductTypeNames();
        for (ProductType p:list){
            System.out.println(p);
        }
    }

}
