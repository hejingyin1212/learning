package com.ly.demo.leetcode;

import javax.activation.MailcapCommandMap;

/**
 * @author ：LY
 * @date ：2021/5/20 11:06
 * @description ：Code481
 */

public class Code481 {
    public static int magicalString(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 4) {
            return 1;
        }
        int res = 1;
        StringBuffer sb = new StringBuffer();
        sb.append(122);
        int i = 2, j = 2;
        while (j < n - 1) {
            if (sb.charAt(i) == '1') {
                if (sb.charAt(j) == '1') {
                    if (sb.length() == 1) {

                    }
                    sb.append(2);
                    j++;
                } else {
                    sb.append(1);
                    j++;
                    res++;
                }
            } else {
                if (sb.charAt(j) == '1') {
                    sb.append(22);
                    j += 2;
                } else {
                    sb.append(11);
                    j += 2;
                    res += 2;
                }
            }
            i++;
            System.out.println(sb.toString());
            System.out.println("i:" + i + "   j:" + j);
        }
        if (sb.length() != n) {
            if (sb.charAt(sb.length() - 1) == '1') {
                res--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        magicalString(10);
    }

}
