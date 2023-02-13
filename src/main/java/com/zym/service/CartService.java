package com.zym.service;

import com.zym.vo.CartVo;

import java.util.HashMap;
import java.util.List;

public interface CartService {

    //将商品添加到购物车
    Integer addToCart(Integer uid,Integer pid,Integer num,String username);

    HashMap<String,Object> findVoByUid(Integer uid, Integer currPage);

    Integer delCartByCid(Integer cid,Integer uid);

    //增加购物车商品数量
    HashMap<String,Object> addCartNumber(Integer uid,Integer cid);

    //减少购物车商品数量
    HashMap<String,Object> downCartNumber(Integer uid,Integer cid);

    List<CartVo> getVoByCid(Integer uid,Integer[] cids);
}
