package com.zym.mapper;

import com.zym.pojo.Address;
import com.zym.pojo.Order;
import com.zym.pojo.OrderItem;
import com.zym.vo.OrderVo;
import com.zym.vo.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface OrderDao {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

    Integer getDefaultAid(); //查询默认地址aid

    /**
     * 根据用户uid获取最新的订单oid
     * @param uid
     * @return
     */
    Integer getOrderOidByUid(Integer uid);

    /**
     * 根据oid查询订单项
     * @param oid
     * @return
     */
    List<OrderItem> getOrderItemByOid(Integer oid);

    Integer updateOrderAddress(@Param("oid") Integer oid, @Param("address") Address address);    //修改订单的收获地址

    Integer updateOrderStatus(Integer oid, Integer uid, Integer status, Date payTime);

    List<Integer> getPidsByOid(Integer oid);  //根据订单项oid查找商品pid集合

    Integer deleteCartByPid(List<Integer> pids);  //根据pid集合删除购物车对应的商品

    List<Integer> selectMyAllOid(Integer status);     //根据status查询所有订单oid

    List<OrderVo> selectMyAllOrderVo(Integer oid);   //根据订单oid查询所有的订单项

    List<ProductVo> getProductVoByOrderOid(Integer oid);

    void updateProductNum(@Param("productVo") ProductVo productVo);

}
