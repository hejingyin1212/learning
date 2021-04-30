package com.ly.demo.leetcode;


import com.google.common.collect.Lists;
import org.opencv.core.Mat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ：LY
 * @date ：2021/4/29 15:17
 * @description ：Code1403
 */

public class Code1403 {

    public List<Integer> minSubsequence(int[] nums) {
//        fastsort(nums, 0, nums.length - 1);
        Arrays.sort(nums);
        int sum = 0;
        for (int i :nums){
            sum += i;
        }
        sum /=2;
        int tmp = 0;
        int right = nums.length - 1;
        ArrayList<Integer> integers = new ArrayList<>();
        while (tmp <= sum) {
            tmp += nums[right];
            integers.add(nums[right]);
            right--;
        }
        return integers;
    }

    private void fastsort(int[] nums, int left, int right) {
        if (right <= left) {
            return;
        }
        int x = nums[left];
        int indexl = left;
        int indexr = right;
        while (left < right) {
            while (left < right) {
                if (nums[right] >= x) {
                    right--;
                } else {
                    break;
                }
            }
            while (left < right) {
                if (nums[left] <= x) {
                    left++;
                } else {
                    break;
                }
            }
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
//            System.out.println(Arrays.toString(nums));
        }
        nums[indexl] = nums[left];
        nums[left] = x;
//        System.out.println(Arrays.toString(nums));
        fastsort(nums, indexl, left - 1);
        fastsort(nums, left + 1, indexr);
    }

    public static void main(String[] args) {


    }
}
