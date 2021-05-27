package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/4/30 10:37
 * @description ：Code258
 */

public class Code258 {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}