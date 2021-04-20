package com.ly.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：LY
 * @date ：2020/11/27 15:07
 * @description ：Solution
 */

public class Solution {
//    public static void main(String[] args) {
//        System.out.println(sortArray(new int[]{5,2,3,1})[0]);
//        System.out.println(sortArray(new int[]{5,2,3,1})[1]);
//        System.out.println(sortArray(new int[]{5,2,3,1})[2]);
//        System.out.println(sortArray(new int[]{5,2,3,1})[3]);
//
//    }
    public static int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    private static void quickSort(int[] arr,int low,int high){
        if (high < low) {return;}
        int a = arr[low];
        int i = low ;
        int j = high;
        int b;
        while (i < j) {
            while (arr[j] >= a && i<j) {
                j--;
            }
            while (arr[i] <= a && i<j) {
                i++;
            }
            if (i<j) {
                b = arr[i];
                arr[i] = arr[j];
                arr[j] = b;
            }
        }
        arr[low] = arr[i];
        arr[i] = a;
        quickSort(arr,low,i-1);
        quickSort(arr,i+1,high);
    }
}
