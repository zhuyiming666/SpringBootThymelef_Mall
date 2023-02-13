package com.zym.mapper;

import com.zym.pojo.Product;
import com.zym.pojo.ProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    List<ProductType> getProductTypeNames();
    List<ProductType> getProductTypes();
    List<Product> getIndexProduct();   //首页商品展示
    Product getProductById(Integer id);
    List<Product> getRandomProduct(@Param("ptypeId") Integer ptypeId, @Param("id") Integer id,@Param("start") Integer start,@Param("end") Integer end);
    int ProductCountByTypeId(@Param("ptypeId") Integer ptypeId);
    List<Product> getProductByTypeId(@Param("ptypeId") Integer ptypeId); //获取不同类别的商品
}
