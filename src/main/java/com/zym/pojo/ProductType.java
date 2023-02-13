package com.zym.pojo;

public class ProductType {

    private int id;
    private String ptypeName;
    private Integer ptypeId;
    private String ptypeUnit;  //单位

    public ProductType() {
    }

    public ProductType(int id,String ptypeName){
        this.id=id;
        this.ptypeName=ptypeName;
    }

    public ProductType(int id, String ptypeName, Integer ptypeId, String ptypeUnit) {
        this.id = id;
        this.ptypeName = ptypeName;
        this.ptypeId = ptypeId;
        this.ptypeUnit = ptypeUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPtypeName() {
        return ptypeName;
    }

    public void setPtypeName(String ptypeName) {
        this.ptypeName = ptypeName;
    }

    public Integer getPtypeId() {
        return ptypeId;
    }

    public void setPtypeId(Integer ptypeId) {
        this.ptypeId = ptypeId;
    }

    public String getPtypeUnit() {
        return ptypeUnit;
    }

    public void setPtypeUnit(String ptypeUnit) {
        this.ptypeUnit = ptypeUnit;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", ptypeName='" + ptypeName + '\'' +
                ", ptypeId=" + ptypeId +
                ", ptypeUnit='" + ptypeUnit + '\'' +
                '}';
    }
}
