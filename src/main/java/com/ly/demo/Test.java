package com.ly.demo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ：LY
 * @date ：2021/2/26 10:57
 * @description ：Test
 */

public class Test {
    public static void main(String[] args) {
        int[] ints = {1,2,3,4,3,2,5,3,5,6,4,3,2,46,426,7,8,9,10,11};
        test(ints,8);
    }

    //数组中两个数a，b之和等于target返回a，b
    private static void test(int[] ints, int target) {
        Set set = new HashSet();
        for (int i = 0; i < ints.length; i++) {
            if (set.contains(ints[i])){
                continue;
            }
            for (int j = 0; j < ints.length; j++) {
                if (set.contains(ints[j])){
                    continue;
                }
                if (ints[i] + ints[j] == target) {
                    System.out.println(ints[i] + "+" + ints[j] + "=" + target);
                    set.add(ints[i]);
                    set.add(ints[j]);
                    break;
                }
            }
        }
    }
}
