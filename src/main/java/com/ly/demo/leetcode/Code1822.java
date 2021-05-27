package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/12 17:18
 * @description ：Code1822
 */

public class Code1822 {

    //初次
    public int arraySign1(int[] nums) {
        int res = 1;
        for (int n : nums) {
            if (n == 0) {
                res *= 0;
            } else if (n < 0) {
                res *= -1;
            }
        }
        return res;
    }

    //第二次
    public int arraySign(int[] nums) {
        int tmp = 0;
        for (int n : nums) {
            if (n == 0) {
                return 0;
            }
            if (n < 0) {
                tmp++;
            }
        }
        return tmp % 2 == 0 ? 1 : -1;
    }
}
