package com.zym.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Music{
    void sing();
}

class Jay implements Music{
    @Override
    public void sing() {
        System.out.println("为你弹奏肖邦的夜曲……");
    }
}

class ProxyJay{   //代理类

    private Music music;

    public ProxyJay(Music music) {
        this.music = music;
    }

    public Object getMusicSing(){
        return Proxy.newProxyInstance(music.getClass().getClassLoader(),
                music.getClass().getInterfaces(),
                (proxy, method, objs) -> {    //lambda
                    System.out.println("预定场地……");
                    Object obj= method.invoke(music,objs);   //方法的灵活调用
                    System.out.println("清理场地……");
                    return obj;
                });
    }
}
//Proxy.newProxyInstance专门用来生成动态代理对象
// music.getClass().getInterfaces()  目标对象实现的所有接口






//测试类
public class ProxyTest {
    public static void main(String[] args) {
        Jay jay=new Jay();
        ProxyJay proxyJay=new ProxyJay(jay);
        Music music=(Music)proxyJay.getMusicSing();
        music.sing();
        System.out.println(music.getClass());
    }
}




