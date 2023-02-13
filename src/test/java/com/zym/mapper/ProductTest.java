package com.zym.mapper;

import com.zym.pojo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {

    @Resource
    private ProductDao productDao;

    @Test
    public void test1(){
        List<Product> list=productDao.getIndexProduct();
        for (Product product:list){
            System.out.println(product);
        }
    }

    @Test
    public void test2(){
        Product product=productDao.getProductById(12);
        System.out.println(product);
    }

    @Test
    public void test3(){
        List<Product> productList=productDao.getRandomProduct(1,11,5,6);
        for (Product product:productList){
            System.out.println(product);
        }
    }

    @Test
    public void test4(){
        int count=productDao.ProductCountByTypeId(1)+1;
        Random random=new Random();
        int start=random.nextInt(count)-3;
        if(start<=0){
            start=0;
        }
        int end=3;
        List<Product> productList=productDao.getRandomProduct(1,11,start,end);
        for (Product product:productList){
            System.out.println(product);
        }
    }

    @Test
    public void test5(){
        List<Product> list=productDao.getProductByTypeId(1);
        System.out.println(Arrays.asList(list));
    }
}
