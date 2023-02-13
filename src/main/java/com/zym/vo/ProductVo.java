package com.zym.vo;

public class ProductVo {
    private Integer pid;
    private Integer num;

    public ProductVo() {
    }

    public ProductVo(Integer pid, Integer num) {
        this.pid = pid;
        this.num = num;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "pid=" + pid +
                ", num=" + num +
                '}';
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
