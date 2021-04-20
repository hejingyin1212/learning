package com.ly.demo.Thread;

/**
 * @author ：LY
 * @date ：2021/4/10 17:23
 * @description ：Res2
 */

public class Res2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Runnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("这是我创建的线程:"+Thread.currentThread().getName());
        }
    }
}
