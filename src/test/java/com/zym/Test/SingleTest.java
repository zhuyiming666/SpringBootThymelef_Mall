package com.zym.Test;

public class SingleTest extends Thread {  //饿汉式

    public static final SingleTest singleTest=new SingleTest();

    public static int count=10;

    private SingleTest(){}

    public static SingleTest getSingleTest(){
        return singleTest;
    }

    public void run() {
        while (count>1){
            count--;
            System.out.println(Thread.currentThread().getName()+"--->"+count);
        }
    }
}

class SingleDemo{
    public static void main(String[] args) {
      SingleTest singleTest=SingleTest.getSingleTest();
      Thread t1=new Thread(singleTest);
      Thread t2=new Thread(singleTest);
      t1.start();
      t2.start();
    }
}
