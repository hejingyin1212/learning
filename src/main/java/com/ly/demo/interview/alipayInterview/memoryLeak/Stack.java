package com.ly.demo.interview.alipayInterview.memoryLeak;

/**
 * @author ：LY
 * @date ：2021/3/3 14:28
 * @description ：栈溢出
 */

public class Stack {
    public static void main(String[] args) {
        new Stack().test();
    }

    public void test() {
        System.out.println(Thread.currentThread().getName());
        test();
    }
}
