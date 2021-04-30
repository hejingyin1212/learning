package com.ly.demo.leetcode;

import java.util.Stack;

/**
 * @author ：LY
 * @date ：2021/4/29 17:26
 * @description ：Code1081
 */

public class Code1081 {
    public String smallestSubsequence(String s) {
        int[] nums = new int[26];
        char[] chars = s.toCharArray();
        int bool = 0;
        for (int i = 0; i < chars.length; i++) {
            nums[chars[i] - 'a'] = i;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (stack.empty()) {
                stack.push(chars[i]);
                bool += 1 << (chars[i] - 'a');
                System.out.println(bool);
                continue;
            }
            if ((bool & (1 << (chars[i] - 'a'))) == (1 << (chars[i] - 'a'))){
                System.out.println(bool);
                continue;
            }
            if (chars[i] >stack.peek()){
                stack.push(chars[i]);
                bool += 1 << (chars[i] - 'a');
            }else {
                if (nums[stack.peek() - 'a']<i){
                    stack.push(chars[i]);
                    bool += 1 << (chars[i] - 'a');
                }else {
                    bool -= 1 << stack.pop() - 'a';
                    i--;
                }
            }
            System.out.println(bool);

        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.empty()){
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.reverse().toString();
    }

    public static void main(String[] args) {
//        Code1081 code1081 = new Code1081();
//        code1081.smallestSubsequence("cbacdcbc");
//        int b = 0;
//        b += 1 << 2;
//        System.out.println(b);
        System.out.println((15 & 4));
    }

}

