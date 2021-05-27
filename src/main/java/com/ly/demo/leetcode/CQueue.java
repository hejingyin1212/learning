package com.ly.demo.leetcode;


import java.util.Stack;

/**
 * @author ：LY
 * @date ：2021/5/24 15:10
 * @description ：CQueue   leetcode-1916
 */

public class CQueue {

    Stack<Integer> in, out;

    public CQueue() {
        in = new Stack();
        out = new Stack();
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {
        if (out.empty()){
            if (in.empty()){
                return -1;
            }else {
                while (!in.empty()){
                    out.push(in.pop());
                }
            }
        }
        return out.pop();
    }
}
