package com.ly.demo.leetcode;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.LinkedList;

/**
 * @author ：LY
 * @date ：2021/3/5 11:29
 * @description ：FindMaxAverage
 */

public class FindMaxAverage {
    public static double findMaxAverage(int[] nums, int k) {
//        double average;
//        LinkedList maxList = new LinkedList();
//        for (int j = 0 ; j < k ;j++){
//            maxList.add(nums[j]);
//        }
//        for (int i = k ; i<nums.length ; i++){
//            if (nums[i] > maxList.)
//            while ()
//        }
        //快速排序
//        int left = 0;
//        int right = nums.length-1;
//        int t = nums[0];
//        while (left < right){
//            if (nums[right] < t)
//        }
        fastSort(nums,0,nums.length-1);
        for (int i : nums){
            System.out.println(i);
        }
        double count = 0;
        for (int i = 0 ; i < k ; i++){
            count += nums[i];
        }
        double average = count / k;
        return average;
    }
    private static void fastSort(int[] a,int left , int right){
        if (right <= left) {
            return;
        }
        int l = left;
        int r = right;
        int temp = a[left];
        while (left < right){
            while (left < right && a[right] <= temp){
                    right--;
            }
            while (left < right && a[left] >= temp){
                    left++;
            }
            if (left < right) {
                exch(a, left, right);
            }
        }
        exch(a,right,l);
        fastSort(a,l,left-1);
        fastSort(a,left+1,r);
    }

    public static void main(String[] args) {
        int[] a = {1,12,-5,-6,50,3};
        System.out.println(findMaxAverage(a,4));
    }
    public static void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
