package com.ly.demo;

import com.ly.entity.TCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LY
 * @date ：2021/1/26 11:13
 * @description ：ListTest
 */

public class ListTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        TCase tCase = new TCase();
        System.out.println(list.size());
        list.add(tCase);
        System.out.println(list.get(0));
        System.out.println(list.size());
        list.add(tCase);
        System.out.println(list.size());
    }
}
