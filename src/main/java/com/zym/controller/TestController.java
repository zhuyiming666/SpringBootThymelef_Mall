package com.zym.controller;

import com.zym.pojo.Product;
import com.zym.pojo.ProductType;
import com.zym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {

    @Resource
    private ProductService productService;

    @RequestMapping("testPage")
    public String testPage(HttpServletRequest request){
        HttpSession session=request.getSession();
        List<ProductType> plist=productService.getProductTypeNames();
        for (ProductType p:plist){
            System.out.println(p);
        }
        session.setAttribute("plist",plist);
        return "test";
    }
    @RequestMapping("testPage1")
    public String testPage1(HttpServletRequest request){

        List<Product> plist=productService.getIndexProduct();
        for (Product p:plist){
            System.out.println(p);
        }
        request.setAttribute("productLists",plist);
        return "test";
    }

}
