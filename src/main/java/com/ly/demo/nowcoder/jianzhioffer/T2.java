package com.ly.demo.nowcoder.jianzhioffer;

/**
 * @author ：LY
 * @date ：2021/3/15 14:41
 * @description ：T2
 */

public class T2 {
    public static String replaceSpace (String s) {
        // write code here
        return s.replace(" ","%20");
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace("We are family"));
    }
}
