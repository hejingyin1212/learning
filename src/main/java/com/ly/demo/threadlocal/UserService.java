package com.ly.demo.threadlocal;

/**
 * @author ：LY
 * @date ：2021/2/25 14:57
 * @description ：UserService
 */

public class UserService {
    public void addUser() {
        System.out.println(Thread.currentThread().getName() + "添加用户信息:" + UserContext.get());
    }
}
