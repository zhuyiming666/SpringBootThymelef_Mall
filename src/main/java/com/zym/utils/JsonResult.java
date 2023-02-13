package com.zym.utils;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {

    private Integer state;   //状态码
    private String message;   //描述信息
    private E date;     //数据

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }
    public JsonResult(Throwable e){
        this.message=e.getMessage();
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(Integer state, E date) {
        this.state = state;
        this.date = date;
    }

    public JsonResult(Integer state, String message, E date) {
        this.state = state;
        this.message = message;
        this.date = date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getDate() {
        return date;
    }

    public void setDate(E date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
