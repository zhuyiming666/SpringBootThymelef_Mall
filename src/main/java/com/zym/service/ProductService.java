package com.zym.service;

import com.zym.pojo.Product;
import com.zym.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {
    List<ProductType> getProductTypeNames();
    List<Product> getIndexProduct();
    Product getProductById(Integer id);
    List<Product> getRandomProduct(Integer ptypeId,Integer id);
    List<Product> getProductByTypeId(Integer ptypeId);
}
