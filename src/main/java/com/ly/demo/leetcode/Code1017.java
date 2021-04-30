package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/4/28 15:04
 * @description ：Code1017
 */

public class Code1017 {
    public String baseNeg2(int N) {
        if (N == 0) {
            return "0";
        }
        int base = -2;
        StringBuffer stringBuffer = new StringBuffer();
        while (N != 1) {
            if (N > 0) {
                stringBuffer.append(N % base);
                N /= base;
            } else {
                int yu = N % base;
                if (yu == -1) {
                    stringBuffer.append(1);
                    N = N / base + 1;
                }else {
                    stringBuffer.append(N % base);
                    N /= base;
                }
            }
        }
        stringBuffer.append(1);
        stringBuffer.reverse();
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println((-13) / (-2));
    }
}
