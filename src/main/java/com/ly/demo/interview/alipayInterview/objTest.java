package com.ly.demo.interview.alipayInterview;


/**
 * @author ：LY
 * @date ：2021/3/1 16:00
 * @description ：objTest
 */

public class objTest {
    public static void main(String[] args) {
        String  a = new String("1");
        String  b = new String("2");
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
//        System.out.println(a.toString());
//        System.out.println(b.toString());
    }
}
