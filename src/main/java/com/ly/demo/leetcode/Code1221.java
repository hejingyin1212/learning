package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/24 14:50
 * @description ：Code1221
 */

public class Code1221 {
    public int balancedStringSplit(String s) {
        int res = 0;
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                flag++;
            } else {
                flag--;
            }
            if (flag == 0) {
                res++;
            }
        }
        return res;
    }
}
