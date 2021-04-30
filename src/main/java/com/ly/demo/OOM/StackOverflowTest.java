package com.ly.demo.OOM;

/**
 * @author ：LY
 * @date ：2021/4/26 20:10
 * @description ：StackOverflowError测试
 */

public class StackOverflowTest {
    static int i = 0;
    public static void main(String[] args) {
        test();
    }
    private static void test(){
        i++;
        test();
    }
}
