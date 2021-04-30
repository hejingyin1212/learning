package com.ly.demo.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：LY
 * @date ：2021/4/27 17:11
 * @description ：全排列
 */

public class Code46 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            ArrayList<Integer> one = new ArrayList<>();
            one.add(nums[0]);
            res.add(one);
            return res;
        }
        for (int num : nums) {
            int[] arr = Arrays.stream(nums).filter(i -> i != num).toArray();
            List<List<Integer>> permute = permute(arr);
            for (List l : permute) {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(num);
                integers.addAll(l);
                res.add(integers);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3, 4}).toString());
    }
}
