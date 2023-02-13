package com.zym.vo;

public class CartVo {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private double price;
    private Integer number;
    private String image;
    private double realPrice;
    private String pName;

    public CartVo() {
    }
    public CartVo(Integer cid, Integer uid, Integer pid, double price, Integer number, String image, double realPrice,String pName) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.number = number;
        this.image = image;
        this.realPrice = realPrice;
        this.pName=pName;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", number=" + number +
                ", image='" + image + '\'' +
                ", realPrice=" + realPrice +
                ", pName='" + pName + '\'' +
                '}';
    }
}
