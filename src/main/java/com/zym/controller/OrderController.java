package com.zym.controller;

import com.zym.pojo.Order;
import com.zym.pojo.OrderItem;
import com.zym.pojo.Users;
import com.zym.service.OrderService;
import com.zym.vo.OrderVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/myorderq.html")
    public String myorderq() {
        return "/myorderq";
    }

    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(String checkCid,HttpSession session,double totalPrice){

        String[] strings=checkCid.split(",");
        int[] ids = Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
        Integer Ids[] = Arrays.stream(ids).boxed().toArray(Integer[]::new);

        Integer aid=orderService.getDefaultAid();
        Users users=(Users)session.getAttribute("users");
        Order order=orderService.createOrder(aid,users.getId(),Ids,totalPrice);
        if(order==null){
            return "F";
        }
        return "T";
    }

    @RequestMapping("/order.html")
    public String order() {
        return "/order";
    }

    @RequestMapping("/showOrderItem")
    @ResponseBody
    public HashMap<String,Object> showOrderItem(HttpSession session){
        Users users=(Users)session.getAttribute("users");
        List<OrderItem> orderItemList=orderService.getOrderItemByOid(users.getId());
        HashMap<String,Object> map=new HashMap<>();
        if (orderItemList!=null){
            map.put("status","T");
            map.put("msg",orderItemList);
            return map;
        }else {
            map.put("status","F");
            map.put("msg","订单数据加载失败!");
            return map;
        }
    }

    @RequestMapping("/updateOrderAddress")
    @ResponseBody
    public HashMap<String,Object> updateOrderAddress(Integer aid,Integer oid,HttpSession session){
        Users users=(Users)session.getAttribute("users");
        Integer rows= orderService.updateOrderAddress(oid,aid,users.getId());
        HashMap<String,Object> map=new HashMap<>();
        if (rows<1){
            map.put("status","F");
            map.put("msg","切换地址失败!");
            return map;
        }else {
            map.put("status","T");
            map.put("msg","地址切换成功!");
            return map;
        }
    }

    @RequestMapping("/ok.html")
    public String ok(HttpSession session,Integer oid){
        Users users=(Users)session.getAttribute("users");
        orderService.updateOrderStatus(oid,users.getId(),1);
        orderService.updateProductNum(oid);
        return "/ok";
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public HashMap<String,Object> cancel(HttpSession session,Integer oid){
        HashMap<String,Object> map=new HashMap<>();
        Users users=(Users)session.getAttribute("users");
        Integer rows=orderService.updateOrderStatus(oid,users.getId(),2);
        if(rows==1){
            map.put("status","T");
            map.put("msg","取消订单成功!");
        }else {
            map.put("status","F");
            map.put("msg","取消订单失败!");
        }
        return map;
    }

    //显示用户全部有效订单
    @RequestMapping("/showMyOrder")
    @ResponseBody
    public HashMap<Integer,List<OrderVo>> showMyOrder(Integer status){
        HashMap<Integer,List<OrderVo>> map=orderService.showMyOrder(status);
        System.out.println(map);
        return map;
    }

    //立即购买
    @RequestMapping("/createSingleOrder")
    @ResponseBody
    public String createSingleOrder(Integer pid){
        return "";
    }

}
