package com.ly.demo.leetcode;


/**
 * @author ：LY
 * @date ：2021/5/19 14:58
 * @description ：Code1716
 */

public class Code1716 {
    public int totalMoney(int n) {
        if (n <= 7) {
            return ((1 + n) * n) / 2;
        }
        int i = n / 7;
        int j = n % 7;
        int res = 28 * i + 7 * i * (i - 1) / 2;
        if (j != 0) {
            res += ((2 * i + j + 1) * j) / 2;
        }
        return res;
    }
}
