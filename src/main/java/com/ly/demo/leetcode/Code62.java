package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/13 14:35
 * @description ：Code62
 */

public class Code62 {
    public int uniquePaths(int m, int n) {
        int[][] ints = new int[m][n];
        for (int i = 0; i < m; i++) {
            ints[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            ints[0][n] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++){
                ints[i][j] = ints[i-1][j]+ints[i][j-1];
            }
        }
        return ints[m-1][n-1];
    }
}
