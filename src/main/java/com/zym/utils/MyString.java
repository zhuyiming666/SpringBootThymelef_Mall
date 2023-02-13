package com.zym.utils;

import org.springframework.util.DigestUtils;

public class MyString {

    public static String getMd5Password(String password, String salt){
        //md5加密算法方法的调用
        for (int i = 0; i < 3; i++) {
            password= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
