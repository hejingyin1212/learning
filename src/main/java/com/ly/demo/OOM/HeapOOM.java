package com.ly.demo.OOM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：LY
 * @date ：2021/4/26 20:08
 * @description ：HeapOOM
 */

public class HeapOOM {
    public static void main(String[] args) {
        List a = new ArrayList();
        while (true){
            a.add(new HeapOOM());

        }    }
}
