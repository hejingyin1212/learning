package com.ly.demo.Thread;

/**
 * @author ：LY
 * @date ：2021/4/10 17:10
 * @description ：Test
 */

public class Test {
    static Integer i = 1;
    static int flag = 0;

    public static void main(String[] args) {
        Thread thread = new MyThead();
        Thread thread2 = new MyThead2();
        thread.start();
        thread2.start();
    }

    static class MyThead extends Thread {
        @Override
        public void run() {

            while (i <= 100 ) {
                while (flag != 1){
                    synchronized (i) {
                        if (i % 3 != 0) {
                            System.out.println(Thread.currentThread().getId()+":"+i++);
                            if (i<=100) {
                                System.out.println(Thread.currentThread().getId() + ":" + i++);
                            }
                            flag = 1;
                        }
                    }
                }

            }
        }
    }

    static class MyThead2 extends Thread {
        @Override
        public void run() {
            while (i <= 100 ) {
                while (flag != 2) {
                    synchronized (i) {
                        if (i % 3 == 0) {
                            System.out.println(Thread.currentThread().getId() + ":" + i++);
                            flag = 2;
                        }
                    }
                }
            }
        }
    }
}



