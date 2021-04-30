package com.ly.demo.interview;

/**
 * @author ：LY
 * @date ：2021/4/26 20:37
 * @description ：SingletonByStaticClass
 */

public class SingletonByStaticClass {

    private SingletonByStaticClass(){}

    private static class Lazy{
        private final static SingletonByStaticClass INSTANCE = new SingletonByStaticClass();
    }

    public static SingletonByStaticClass getInstance(){
        return Lazy.INSTANCE;
    }

}
