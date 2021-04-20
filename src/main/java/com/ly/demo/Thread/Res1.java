package com.ly.demo.Thread;

/**
 * @author ：LY
 * @date ：2021/4/10 17:10
 * @description ：Res1
 */

public class Res1 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new MyThread();
            thread.start();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("这是我创建的线程:" + getName());
        }
    }
}