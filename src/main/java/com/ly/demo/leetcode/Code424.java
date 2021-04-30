package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/4/27 15:12
 * @description ：Code424
 */

public class Code424 {
    public static int characterReplacement(String s, int k) {
        int result = 0;
        int i = 0;
        int j;
        while (i < s.length()) {
            int tmp = 0;
            int count = 0;
            j = i;
            while (j < s.length()) {
                if (s.charAt(j) == s.charAt(i)) {
                    tmp ++;
                    j++;
                } else {
                    count++;
                    j++;
                    if (count>k){
                        break;
                    }
                    else {
                        tmp++;
                    }
                }
            }
            result = tmp>result?tmp:result;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
    }
}
