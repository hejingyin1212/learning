package com.ly.demo.OOM;

import java.util.concurrent.TimeUnit;

/**
 * @author ：LY
 * @date ：2021/4/26 20:24
 * @description ：StackOOM
 */

public class StackOOM {
    public static void main(String[] args) {
        while (true){
            Thread t = new Thread(()->{
                while (true){
                    try {
                        TimeUnit.HOURS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}
