package com.ly.demo.threadlocal;

import com.aspose.words.Run;

/**
 * @author ：LY
 * @date ：2021/4/1 15:14
 * @description ：Thread1
 */

public class Thread1 implements Runnable {

    private static int num;


    @Override
    public void run() {
        num = 3;
        System.out.println(Thread.currentThread().getName() + " num:" + num);
        num = 5;
        System.out.println(Thread.currentThread().getName() + " num:" + num * 2);
    }

    public static void main(String[] args) {
        Thread1 thread = new Thread1();
        for (int i = 0; i < 100; i++) {
            new Thread(thread, "thread-" + i).start();
        }
    }
}
