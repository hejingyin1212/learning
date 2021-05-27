package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/20 15:09
 * @description ：Code1437
 */

public class Code1437 {
    public boolean kLengthApart(int[] nums, int k) {
        int tmp = -1;
        for (int i = 0;i<nums.length;i++){
            if (nums[i] == 1){
                if (tmp !=-1 &&i-tmp-1<k){
                    return false;
                }else {
                    tmp = i;
                }
            }
        }
        return true;
    }
}
