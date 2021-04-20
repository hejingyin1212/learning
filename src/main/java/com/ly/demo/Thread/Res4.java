package com.ly.demo.Thread;

import java.util.concurrent.*;

/**
 * @author ：LY
 * @date ：2021/4/10 17:46
 * @description ：Res4
 */

public class Res4 {
    BlockingQueue queue = new ArrayBlockingQueue(10);
    ThreadPoolExecutor pool = new ThreadPoolExecutor(3,3, 2000L, TimeUnit.MILLISECONDS,queue);
}
