package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/3/5 15:01
 * @description ：MaxLinkAverage
 */

public class MaxLinkAverage {
    public double findMaxAverage(int[] nums, int k) {
        int count = 0;
        for (int i = 0 ; i<k ; i++){
            count += nums[i];
        }
        int maxCount = count;
        for (int j = k ; j <nums.length;j++){
            count = count +nums[j]-nums[j-k];
            if (count > maxCount){
                maxCount = count;
            }
        }
        double max = (double)maxCount;
        return max/k;
    }
}
