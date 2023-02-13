package com.zym.mapper;

import com.zym.pojo.Order;
import com.zym.pojo.OrderItem;
import com.zym.vo.OrderVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDaoTest {

    @Resource
    private OrderDao orderDao;

    @Test
    public void test01(){
        Order order=new Order();
        order.setRecvName("朱一鸣");
        order.setRecvPhone("110");
        order.setUid(2);
        Integer x=orderDao.insertOrder(order);
        System.out.println(x);
    }
    @Test
    public void test02(){
        OrderItem orderItem=new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(1);
        orderItem.setTitle("手机");
        Integer x=orderDao.insertOrderItem(orderItem);
        System.out.println("x="+x);
    }

    @Test
    public void test03(){
        List<Integer> oids= orderDao.selectMyAllOid(1);
        System.out.println(oids);

        List<OrderVo> orderVos=orderDao.selectMyAllOrderVo(1);
        System.out.println(Arrays.asList(orderVos));
    }
}
