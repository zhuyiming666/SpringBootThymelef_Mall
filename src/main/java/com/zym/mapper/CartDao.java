package com.zym.mapper;

import com.zym.pojo.Cart;
import com.zym.vo.CartVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//购物车接口
public interface CartDao {

    Integer insertCart(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id和商品的id查询购物车中的数据
     * @param uid
     * @param pid
     * @return
     */
    Cart findByUidAndPid(Integer uid,Integer pid);


    //根据用户uid查找对应的购物车数据
    List<CartVo> findVoByUid(Integer uid,Integer start,Integer size);

    //根据cid删除用户购物车数据
    Integer delCartByCid(Integer cid,Integer uid);

    //增加购物车商品数量
    Integer addCartNumber(Integer uid,Integer cid);

    //减少购物车商品数量
    Integer downCartNumber(Integer uid,Integer cid);

    //查找用户uid对应的购物车数量
    Integer findCountByUid(Integer uid);

    //查询购物车对应商品的数量和实际价格
    HashMap<String,Object> findCartNumber(Integer uid, Integer cid);

    List<CartVo> getVoByCid(@Param("uid") Integer uid, @Param("cids") Integer cids[]);


}
