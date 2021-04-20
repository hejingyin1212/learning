package com.ly.demo.nowcoder.jianzhioffer;

import javax.activation.MailcapCommandMap;

/**
 * @author ：LY
 * @date ：2021/3/15 13:46
 * @description ：T1
 */

public class T1 {
    public static boolean Find(int target, int [][] array) {
        int a = array.length;
        if (a == 0){
            return false;
        }
        int b = array[0].length;
        if (b == 0){
            return false;
        }
        int i = 0; //hang
        int j = b - 1; //列
        while (i < a && j >= 0){
            int t = array[i][j];
            if (t == target){
                return true;
            }
            if (t > target){
                j--;
            }
            if (t < target){
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int a[][] = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        System.out.println(Find(7,a));
    }
}
