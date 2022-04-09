package com.zjj.jiayou.Test;/*
 *author zhujunjia
 *version 1.0
 */
@SuppressWarnings({"all"})
public class Test {
    public static void main(String[] args) {
        A a = new A();
        Thread thread = new Thread(a);
        Thread thread1 = new Thread(a);
        thread.start();
        thread1.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.a=100;
    }
}

class A implements Runnable{
    int a=0;
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName()+a);
        }

    }
}