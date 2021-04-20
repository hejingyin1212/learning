package com.ly.demo.java8;

/**
 * @author ：LY
 * @date ：2021/4/19 16:02
 * @description ：FunctionalInterface
 */

public class FunctionalInterface {
    public static void main(String[] args) {

    }
    public interface Vehicle {
        default void print(){
            System.out.println("我是一辆车!");
        }
        // 静态方法
        static void blowHorn(){
            System.out.println("按喇叭!!!");
        }
    }
}
