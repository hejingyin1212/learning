package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/4/20 11:00
 * @description ：Code661
 */

public class Code661 {
    public static int[][] imageSmoother(int[][] M) {
        int[][] N = new int[M.length][];
        for (int i = 0 ;i<M.length;i++){
            for (int j = 0;j<M[0].length;j++){
                N[i][j] = pointSmoother(M,i,j);
            }
        }
        return N;
    }

    private static int pointSmoother(int[][] M, int i, int j) {
        int sum = 0;
        int count = 0;
        int top = i > 0 ? i - 1 : i;
        int under = i + 1 < M[1].length ? i + 1 : i;
        int left = j > 0 ? j - 1 : j;
        int right = j + 1 < M.length ? j + 1 : j;
        while (top<=under){
            while (left<=right){
                sum += M[top][left];
                count++;
                right++;
            }
            top++;
        }
        return sum/count;
    }

    public static void main(String[] args) {
        System.out.println(imageSmoother(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}).toString());
    }
}
