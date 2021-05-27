package com.ly.demo.leetcode;

import java.util.Arrays;

/**
 * @author ：LY
 * @date ：2021/5/10 15:04
 * @description ：Code1044
 */

public class Code1044 {
    //后缀数组法
    public String longestDupSubstring1(String s) {
        String res = "";
        if (s.length() == 0 || s.length() == 1) {
            return res;
        }
        String[] list = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            list[i] = s.substring(i);
        }
        Arrays.sort(list);
        for (int i = 1; i < s.length(); i++) {
            String s0 = list[i - 1], s1 = list[i];
            int j = 0;
            while (j<s0.length()&&j<s1.length()&&s0.charAt(j) == s1.charAt(j)){
                j++;
            }
            if (j>res.length()){
                res = s0.substring(0,j);
            }
        }
        return res;
    }

    //字符串编码法
//    public String longestDupSubstring(String s){
//
//    }

}
