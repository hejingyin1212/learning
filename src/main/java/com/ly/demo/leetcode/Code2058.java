package com.ly.demo.leetcode;


import java.util.Stack;

/**
 * @author ：LY
 * @date ：2021/4/30 14:28
 * @description ：Code2058
 */

public class Code2058 {
    //自己写的，性能极差
    public int trap(int[] height) {
        int max = 0;
        for (int i : height) {
            if (i > max) {
                max = i;
            }
        }
        if (max == 0) {
            return 0;
        }
        boolean flag = false;
        int tmp = 0;
        int res = 0;
        for (int i = 1; i <= max; i++) {
            for (int h : height) {
                if (h >= i) {
                    if (!flag) {
                        flag = true;
                        continue;
                    } else {
                        res += tmp;
                        tmp = 0;
                    }
                } else {
                    if (flag) {
                        tmp++;
                    }
                }
            }
            tmp = 0;
            flag = false;
        }
        return res;
    }

    //动态规划
    public int trap2(int[] height) {

        int max = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < left.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        right[height.length - 1] = height[height.length - 1];
        for (int j = height.length - 2; j >= 0; j--) {
            right[j] = Math.max(right[j + 1], height[j]);
        }
        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int m = Math.min(left[i], right[i]);
            if (m > height[i]) {
                res += (m - height[i]);
            }
        }
        return res;
    }

    //单调栈
    public int trap3(int[] height) {
        Stack<Integer> stack = new Stack();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int index = stack.pop();
                if (!stack.empty()) {
                    sum += (Math.min(height[stack.peek()], height[i]) - height[index]) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return sum;
    }

    //双标法
    public int trap4(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                res += leftMax-height[left];
                left++;
            }else {
                res+=rightMax-height[right];
                right--;
            }
        }
        return res;
    }

}
