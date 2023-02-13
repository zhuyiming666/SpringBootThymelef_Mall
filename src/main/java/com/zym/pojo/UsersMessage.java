package com.zym.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UsersMessage {
    private Integer id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birth;
    private Integer sex;
    private String area;
    private String phone;
    private String avatar;

    public UsersMessage() {
    }

    public UsersMessage(Integer id, String name, Date birth, Integer sex, String area, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.area = area;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UsersMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", sex=" + sex +
                ", area='" + area + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
