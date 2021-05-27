package com.ly.demo.leetcode;

import org.apache.ibatis.javassist.expr.NewArray;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：LY
 * @date ：2021/5/18 13:49
 * @description ：Code218
 */

public class Code218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int max = 0;
        for (int[] ints : buildings) {
            max = Math.max(max, ints[1]);
        }
        int[] tmp = new int[max];
        for (int[] ints : buildings) {
            for (int i = ints[0]; i <= ints[1]; i++) {
                if(i == max){
                    continue;
                }
                tmp[i] = Math.max(tmp[i], ints[2]);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        if (tmp[0] != 0) {
            List<Integer>  list = Arrays.asList(0,tmp[0]);
            res.add(list);
        }
        for (int i = 1; i < tmp.length; i++) {
            if (tmp[i] > tmp[i-1]){
                List<Integer>  list = Arrays.asList(i,tmp[i]);
                res.add(list);
            }
            if (tmp[i] < tmp[i-1]){
                List<Integer>  list = Arrays.asList(i-1,tmp[i]);
                res.add(list);
            }
        }
        List<Integer>  list2 = Arrays.asList(max,0);
        res.add(list2);
        return res;
    }

}
