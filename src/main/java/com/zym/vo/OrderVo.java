package com.zym.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderVo {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private double totalPrice;
    private String title;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderTime;       //订单创建时间
    private double price;


    public OrderVo() {
    }

    public OrderVo(Integer oid, Integer uid, String recvName, String recvPhone, double totalPrice, String title, String image,Date orderTime,double price) {
        this.oid = oid;
        this.uid = uid;
        this.recvName = recvName;
        this.recvPhone = recvPhone;
        this.totalPrice = totalPrice;
        this.title = title;
        this.image = image;
        this.orderTime=orderTime;
        this.price=price;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvName() {
        return recvName;
    }

    public void setRecvName(String recvName) {
        this.recvName = recvName;
    }

    public String getRecvPhone() {
        return recvPhone;
    }

    public void setRecvPhone(String recvPhone) {
        this.recvPhone = recvPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", recvName='" + recvName + '\'' +
                ", recvPhone='" + recvPhone + '\'' +
                ", totalPrice=" + totalPrice +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", orderTime=" + orderTime +
                ", price=" + price +
                '}';
    }
}
