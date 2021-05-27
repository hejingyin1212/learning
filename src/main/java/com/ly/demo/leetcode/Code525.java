package com.ly.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ：LY
 * @date ：2021/5/11 10:28
 * @description ：Code525
 */

//计算count，相同的两者之间0、1数量相同
public class Code525 {

    //使用int[nums.length * 2 + 1]
    public int findMaxLength1(int[] nums) {
        int res = 0;
        int count = 0;
        int[] list = new int[nums.length * 2 + 1];
        Arrays.fill(list, -2);
        list[nums.length] = -1;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 0 ? -1 : 1);
            if (list[count + nums.length] <= -2) {
                list[count + nums.length] = i;
            } else {
                res = Math.max(res, i - list[count + nums.length]);
            }
        }
        return res;
    }

    //使用Map<Integer, Integer>
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(count)) {
                res = Math.max(res, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return res;
    }

}
