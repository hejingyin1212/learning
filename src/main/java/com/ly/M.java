package com.ly;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LY
 * @date ：2020/9/10 14:10
 * @description ：M
 */

public class M {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        List list1 = list.subList(1, 2);
        System.out.println(list1.size());
        System.out.println(list1.get(0));
    }
}
