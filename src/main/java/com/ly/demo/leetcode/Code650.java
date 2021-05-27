package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/20 14:26
 * @description ：Code650
 */

public class Code650 {
    public int minSteps(int n) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Code650 code650 = new Code650();
        System.out.println(code650.minSteps(27));
    }
}
