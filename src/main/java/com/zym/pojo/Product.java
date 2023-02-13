package com.zym.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Product {

    private int id;
    private String pname;
    private double price;
    private int pvirtual;  //销售量
    private String psketch;  //描述
    private String pimg;
    private Date pdate;   //上架日期
    private int pstock;    //库存
    private Integer pstatus;  //商品状态
    private Integer ptypeId;
    private ProductType productType;

    public Product() {
    }

    public Product(int id, String pname, double price, int pvirtual, String psketch, String pimg, Date pdate, int pstock, Integer pstatus, Integer ptypeId, ProductType productType) {
        this.id = id;
        this.pname = pname;
        this.price = price;
        this.pvirtual = pvirtual;
        this.psketch = psketch;
        this.pimg = pimg;
        this.pdate = pdate;
        this.pstock = pstock;
        this.pstatus = pstatus;
        this.ptypeId = ptypeId;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPvirtual() {
        return pvirtual;
    }

    public void setPvirtual(int pvirtual) {
        this.pvirtual = pvirtual;
    }

    public String getPsketch() {
        return psketch;
    }

    public void setPsketch(String psketch) {
        this.psketch = psketch;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getPstock() {
        return pstock;
    }

    public void setPstock(int pstock) {
        this.pstock = pstock;
    }

    public Integer getPstatus() {
        return pstatus;
    }

    public void setPstatus(Integer pstatus) {
        this.pstatus = pstatus;
    }

    public Integer getPtypeId() {
        return ptypeId;
    }

    public void setPtypeId(Integer ptypeId) {
        this.ptypeId = ptypeId;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pvirtual=" + pvirtual +
                ", psketch='" + psketch + '\'' +
                ", pimg='" + pimg + '\'' +
                ", pdate=" + pdate +
                ", pstock=" + pstock +
                ", pstatus=" + pstatus +
                ", ptypeId=" + ptypeId +
                ", productType=" + productType +
                '}';
    }
}