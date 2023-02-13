package com.zym.service.Imp;

import com.zym.mapper.CartDao;
import com.zym.mapper.ProductDao;
import com.zym.pojo.Cart;
import com.zym.pojo.Product;
import com.zym.service.CartService;
import com.zym.vo.CartVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {

    @Resource
    private CartDao cartDao;

    @Resource
    private ProductDao productDao;

    @Override
    public Integer addToCart(Integer uid, Integer pid, Integer num, String username) {
        //查询当前要添加的这个商品是否在购物车表中存在
        Cart result=cartDao.findByUidAndPid(uid,pid);
        Date date =new Date();
        if(result==null){  //表示这个商品重来没有在购物车中存在
            Cart cart=new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(num);

            //价格，来自于商品中的数据
            Product product=productDao.getProductById(pid);
            cart.setPrice(product.getPrice());

            //补全4日志
            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);

            Integer rows=cartDao.insertCart(cart);
            if(rows!=1){
                return -1;
            }
        }else {          //这个商品在购物车中已经存在(只需更新num值)
              Integer number=result.getNum()+num;
              Integer rows=cartDao.updateNumByCid(result.getCid(),number,username,date);
              if (rows!=1){
                  return -1;
              }
        }
        return 1;
    }

    @Override
    public HashMap<String,Object> findVoByUid(Integer uid, Integer currPage) {
        int size=3;
        int totalCount=cartDao.findCountByUid(uid);
        int pageCount;
        if (totalCount%size==0){
            pageCount=totalCount/size;
        }else {
            pageCount=(totalCount/size)+1;
        }

        if(currPage<=1){
            currPage=1;
        }
        if(currPage>=pageCount){
            currPage=pageCount;
        }
        int start=(currPage-1)*size;



        List<CartVo> cartVos=cartDao.findVoByUid(uid,start,size);

        HashMap<String,Object> map=new HashMap<>();
        map.put("cartVos",cartVos);
        map.put("pageCount",pageCount);
        map.put("totalCount",totalCount);
        map.put("currPage",currPage);

        return map;
    }

    @Override
    public Integer delCartByCid(Integer cid, Integer uid) {
        return cartDao.delCartByCid(cid,uid);
    }

    @Override
    public HashMap<String,Object> addCartNumber(Integer uid, Integer cid) {
        Integer x=cartDao.addCartNumber(uid,cid);
        if(x<1){
            return null;
        }
        HashMap<String,Object> map=cartDao.findCartNumber(uid,cid);
        return map;
    }

    @Override
    public HashMap<String,Object> downCartNumber(Integer uid, Integer cid) {
        Integer x=cartDao.downCartNumber(uid,cid);
        if(x<1){
            return null;
        }
        HashMap<String,Object> map=cartDao.findCartNumber(uid,cid);
        return map;
    }

    @Override
    public List<CartVo> getVoByCid(Integer uid, Integer[] cids) {
        return cartDao.getVoByCid(uid,cids);
    }
}
