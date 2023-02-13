package com.zym.service;

import com.zym.pojo.Address;
import com.zym.pojo.Order;
import com.zym.pojo.OrderItem;
import com.zym.vo.OrderVo;

import java.util.HashMap;
import java.util.List;

public interface OrderService {

    Order createOrder(Integer aid, Integer uid, Integer[] cids,double totalPrice);
    Integer getDefaultAid(); //查询默认地址aid
    List<OrderItem> getOrderItemByOid(Integer uid);

    Integer updateOrderAddress(Integer oid,Integer aid,Integer uid);

    Integer updateOrderStatus(Integer oid,Integer uid,Integer status);

    HashMap<Integer,List<OrderVo>> showMyOrder(Integer status);

    void updateProductNum(Integer oid);

}
