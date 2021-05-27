package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/20 10:27
 * @description ：Code475
 */

public class Code475 {
    public int findRadius(int[] houses, int[] heaters) {
        int res = 0;
        for (int i:houses){
            int tmp = Integer.MAX_VALUE;
            for (int j:heaters){
                if (i-j == 0){
                    tmp = 0;
                    break;
                }
                tmp = Math.min(tmp,Math.abs(i-j));
            }
            res = Math.max(res,tmp);
        }
        return res;
    }
}
