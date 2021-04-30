package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/4/20 10:51
 * @description ：Code28
 */

public class Code28 {
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(strStr("aaabaa", ""));
    }
}
