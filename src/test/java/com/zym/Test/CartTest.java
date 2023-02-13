package com.zym.Test;


class BenChi{

    public void start() {
        System.out.println("启动我的奔驰……");
    }
}

class BaoMa{

    public void start() {
        System.out.println("启动我的宝马……");
    }
}

class CartFactory{    //工厂类

    public static Object getMyCart(String cartName){
        if(cartName==null||cartName==""){
            return null;
        }
        switch (cartName){
            case "benchi":
                return new BenChi();
            case "baoma":
                return new BaoMa();
            default:
                return null;
        }
    }
}

public class CartTest {

    public static void main(String[] args) {

        BenChi benChi= (BenChi) CartFactory.getMyCart("benchi");
        benChi.start();

        BaoMa baoMa=(BaoMa)CartFactory.getMyCart("baoma");
        baoMa.start();
    }
}
