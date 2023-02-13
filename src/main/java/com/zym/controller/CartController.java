package com.zym.controller;

import com.zym.pojo.Users;
import com.zym.service.CartService;
import com.zym.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping({"/cart.html", "/"})
    public String goToCart() {
        return "/cart";
    }

    //显示购物车所有数据
    @RequestMapping("/showCart/{currPage}")
    @ResponseBody
    public HashMap<String,Object> showCart(@PathVariable("currPage")Integer currPage,HttpSession session) {
        Users users = (Users) session.getAttribute("users");
        Integer uid = users.getId();
        HashMap<String,Object> map= cartService.findVoByUid(uid,currPage);
        return map;
    }

    //添加到购物车
    @RequestMapping("/addCart")
    @ResponseBody
    public HashMap<String, Object> addCart(Integer pid, Integer number, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        Users users = (Users) session.getAttribute("users");
        Integer uid = users.getId();
        String username = users.getEmail();
        Integer x = cartService.addToCart(uid, pid, number, username);
        if (x < 1) {
            map.put("status", "F");
            map.put("msg", "添加到购物车失败!");
        } else {
            map.put("status", "T");
            map.put("msg", "添加到购物车成功!");
        }
        return map;
    }

    //删除购物车
    @RequestMapping("/delCart/{cid}")
    @ResponseBody
    public HashMap<String, Object> delCart(@PathVariable("cid") Integer cid, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        Users users = (Users) session.getAttribute("users");
        Integer uid = users.getId();
        Integer x = cartService.delCartByCid(cid, uid);
        if (x == 1) {
            map.put("status", "T");
            map.put("msg", "删除购物车成功!");
        } else {
            map.put("status", "F");
            map.put("msg", "删除购物车失败!");
        }
        return map;
    }

    //增加购物车数量
    @RequestMapping("/addNumber/{cid}")
    @ResponseBody
    public HashMap<String, Object> addCartNumber(@PathVariable("cid") Integer cid, HttpSession session) {

        Users users = (Users) session.getAttribute("users");
        Integer uid = users.getId();
        HashMap<String, Object> map= cartService.addCartNumber(uid, cid);
        return map;
    }

    //减少购物车数量
    @RequestMapping("/downNumber/{cid}")
    @ResponseBody
    public HashMap<String, Object> downCartNumber(@PathVariable("cid") Integer cid, HttpSession session) {

        Users users = (Users) session.getAttribute("users");
        Integer uid = users.getId();
        HashMap<String, Object> map= cartService.downCartNumber(uid, cid);
        return map;
    }
}
