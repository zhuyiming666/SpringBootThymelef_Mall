package com.zym.service;

import com.zym.pojo.Order;
import com.zym.vo.OrderVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Resource
    OrderService orderService;

    @Test
    public void test1(){
        Integer cids[]={3,4};
        Order order=orderService.createOrder(1,2,cids,300.00);
        System.out.println(order);
    }

    @Test
    public void test2(){
        HashMap<Integer, List<OrderVo>> map= orderService.showMyOrder(1);
        System.out.println(map);
    }
}
