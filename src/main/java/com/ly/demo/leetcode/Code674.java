package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/6 17:29
 * @description ：Code674
 */

public class Code674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 1||nums.length == 0){
            return nums.length;
        }
        int left = 0,right = 0;
        int len = 0;
        while (right<nums.length){
            if (left == right){
                right++;
            }else {
                if (nums[right]>nums[right-1]){
                    right++;
                }else {
                    len = Math.max(len,right-left);
                    left = right;
                }
            }
        }
        len = Math.max(len,right-left);
        return len;
    }
}
