package com.zym.controller;

import com.zym.pojo.Product;
import com.zym.pojo.ProductType;
import com.zym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private ProductService productService;

   /* @RequestMapping("/indexShow")
    public String indexShow(HttpServletRequest request){
        HttpSession session=request.getSession();
        List<ProductType> plist=productService.getProductTypeNames();
        for (ProductType p:plist){
            System.out.println(p);
        }
        session.setAttribute("plist",plist);

        return "redirect:/index.html";
    }*/

    @RequestMapping(value = {"/index","/","/index.html"})
    public String indexPage(HttpServletRequest request){
        HttpSession session=request.getSession();
        List<ProductType> plist=productService.getProductTypeNames();
        session.setAttribute("plist",plist);

        List<Product> productList=getIndexProducts();
        request.setAttribute("productLists",productList);
        return "index";
    }

    /*@RequestMapping("/index.html")
    public String mapperIndex(){
        return "indexs";
    }*/

    @RequestMapping("/proDetail")
    public String proDetail(Integer id,Integer ptypeId,HttpServletRequest request){
        Product product=productService.getProductById(id);
        request.setAttribute("productDetail",product);

        List<Product> productRandom=productService.getRandomProduct(ptypeId,id);
        request.setAttribute("productRandom",productRandom);
        return "/proDetail";
    }

    @RequestMapping("/ifLoginExits")
    @ResponseBody
    public String ifLogin(HttpSession session){
        if(session.getAttribute("users")!=null){
            return "T";
        }
        return "F";
    }

    @RequestMapping("/order.html")
    public String order(Integer id){
        System.out.println("======id="+id+"=====");
        return "order";
    }


    //获取首页商品集合
    public List<Product> getIndexProducts(){
        List<Product> plist=productService.getIndexProduct();
        return plist;
    }

    @RequestMapping("/productByTypeId")
    public String getProductByTypeId(Integer ptypeId,HttpServletRequest request){
        List<Product> list=productService.getProductByTypeId(ptypeId);
        System.out.println(Arrays.asList(list));
        request.setAttribute("productLists",list);
        return "product";
    }
}
