package com.ly.demo.interview.alipayInterview.memoryLeak;

import javax.naming.Context;

/**
 * @author ：LY
 * @date ：2021/3/3 10:58
 * @description ：MemoryLeakBySingleton
 */

public class MemoryLeakBySingleton {
    private static MemoryLeakBySingleton instance;
    private Context context;
    private MemoryLeakBySingleton(Context context){
        this.context = context;
    }
    public MemoryLeakBySingleton getInstance(){
        if (instance == null){
            instance = new MemoryLeakBySingleton(context);
        }
        return instance;
    }
}
