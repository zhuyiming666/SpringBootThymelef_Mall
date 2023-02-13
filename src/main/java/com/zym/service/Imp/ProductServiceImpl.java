package com.zym.service.Imp;

import com.zym.mapper.ProductDao;
import com.zym.pojo.Product;
import com.zym.pojo.ProductType;
import com.zym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public List<ProductType> getProductTypeNames() {
        return productDao.getProductTypeNames();
    }

    @Override
    public List<Product> getIndexProduct() {
        return productDao.getIndexProduct();
    }

    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getRandomProduct(Integer ptypeId, Integer id) {
        int count=productDao.ProductCountByTypeId(1)+1;
        Random random=new Random();
        int start=random.nextInt(count)-3;
        if(start<=0){
            start=0;
        }
        int end=3;
        List<Product> productList=productDao.getRandomProduct(ptypeId,id,start,end);
        return productList;
    }

    @Override
    public List<Product> getProductByTypeId(Integer ptypeId) {
        return productDao.getProductByTypeId(ptypeId);
    }
}
