package com.ly.demo.leetcode;

import java.util.List;

/**
 * @author ：LY
 * @date ：2021/4/30 9:22
 * @description ：Code1331
 */

public class Code1331 {
    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) {
            return new int[]{};
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : arr) {
            max = max < a ? a : max;
            min = min > a ? a : min;
        }
        int[] contain = new int[max - min + 1];
        for (int a : arr) {
            contain[a - min] = 1;
        }
        int[] sum = new int[contain.length];
        for (int j = 1; j < sum.length; j++) {
            sum[j] = sum[j - 1] + contain[j - 1];
        }
        int[] res = new int[arr.length];
        int index = 0;
        for (int n : arr) {
            res[index++] = sum[n - min] + 1;
        }
        return res;
    }
}
