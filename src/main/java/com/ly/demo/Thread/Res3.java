package com.ly.demo.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author ：LY
 * @date ：2021/4/10 17:32
 * @description ：Res3
 */

public class Res3 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Callable myCallable = new MyCallable();
            FutureTask futureTask = new FutureTask(myCallable);
            Thread thread = new Thread(futureTask);
            thread.start();
//            try {
//                System.out.println(futureTask.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class MyCallable implements Callable{

        @Override
        public String call() throws Exception {
            System.out.println("这是我创建的线程:"+Thread.currentThread().getName());
            return Thread.currentThread().getName();
        }
    }
}
