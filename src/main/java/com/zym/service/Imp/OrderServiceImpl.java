package com.zym.service.Imp;

import com.zym.mapper.OrderDao;
import com.zym.mapper.UsersMessagesDao;
import com.zym.pojo.Address;
import com.zym.pojo.Order;
import com.zym.pojo.OrderItem;
import com.zym.service.CartService;
import com.zym.service.OrderService;
import com.zym.service.UsersMessageService;
import com.zym.vo.CartVo;
import com.zym.vo.OrderVo;
import com.zym.vo.ProductVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UsersMessageService usersMessageService;

    @Resource
    private CartService cartService;

    @Override
    public Order createOrder(Integer aid, Integer uid, Integer[] cids,double totalPrice) {
        List<CartVo> list = cartService.getVoByCid(uid, cids);
        Address address = usersMessageService.getAddressByAid(aid, uid);

        Order order = new Order();
        order.setUid(uid);

        //收获地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvAddress(address.getAddress());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvArea(address.getAreaName());
        order.setRecvCity(address.getCityName());

        //支付，总价
        order.setStatus(0);   //订单刚创建时设置为未支付
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());

        orderDao.insertOrder(order);

        for (CartVo cv :
                list) {
            totalPrice += cv.getRealPrice() * cv.getNumber();
            //创建订单项数据对象
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cv.getPid());
            orderItem.setTitle(cv.getpName());
            orderItem.setImage(cv.getImage());
            orderItem.setPrice(cv.getRealPrice());
            orderItem.setNum(cv.getNumber());
            orderItem.setCreatedTime(new Date());

            orderDao.insertOrderItem(orderItem);
        }
        return order;
    }

    @Override
    public Integer getDefaultAid() {
        return orderDao.getDefaultAid();
    }

    @Override
    public List<OrderItem> getOrderItemByOid(Integer uid) {
        Integer oid=orderDao.getOrderOidByUid(uid);
        System.out.println("oid====="+oid) ;
        List<OrderItem> orderItems=orderDao.getOrderItemByOid(oid);
        return orderItems;
    }

    @Override
    public Integer updateOrderAddress(Integer oid,Integer aid,Integer uid) {
        Address address = usersMessageService.getAddressByAid(aid, uid);
        Integer rows=orderDao.updateOrderAddress(oid,address);
        return rows;
    }

    @Override
    public Integer updateOrderStatus(Integer oid, Integer uid, Integer status) {
        Date payTime=null;
        if (status==1){
            payTime=new Date();
        }
        Integer rows=orderDao.updateOrderStatus(oid,uid,status,payTime);
        if(rows==1&&status==1){  //支付成功,删除购物车中的数据
           List<Integer> pids=orderDao.getPidsByOid(oid);  //根据订单项oid查找商品pid集合
           Integer delRows=orderDao.deleteCartByPid(pids);
           return delRows;
        }
        return rows;
    }

    @Override
    public HashMap<Integer, List<OrderVo>> showMyOrder(Integer status) {
        HashMap<Integer,List<OrderVo>> map=new HashMap<>();
        List<Integer> oids= orderDao.selectMyAllOid(status);
        Integer count=0;
        for (Integer oid:oids){
             map.put(count++,orderDao.selectMyAllOrderVo(oid));
        }
        return map;
    }

    @Override
    public void updateProductNum(Integer oid) {
        List<ProductVo> productVoList= orderDao.getProductVoByOrderOid(oid);
        for(ProductVo productVo:productVoList){
            orderDao.updateProductNum(productVo);
        }
    }
}
