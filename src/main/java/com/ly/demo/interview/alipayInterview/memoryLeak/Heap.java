package com.ly.demo.interview.alipayInterview.memoryLeak;

import java.util.ArrayList;

/**
 * @author ：LY
 * @date ：2021/3/3 14:32
 * @description ：Heap溢出
 */


public class Heap {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        while (true) {
            list.add(new Heap());
        }
    }
}
