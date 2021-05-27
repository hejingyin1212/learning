package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/6 17:04
 * @description ：Code209
 */

public class Code209 {
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE, sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                res = Math.min(right - left + 1, res);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
