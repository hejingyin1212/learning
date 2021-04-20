package com.ly.demo.threadlocal;

/**
 * @author ：LY
 * @date ：2021/2/25 14:51
 * @description ：UserContext
 */

public class UserContext {
    private static final ThreadLocal<User> CONTEXT = new ThreadLocal<>();

    public static void set(User user){
        CONTEXT.set(user);
    }

    public static User get(){
        return CONTEXT.get();
    }
    public static void remove() {
        CONTEXT.remove();
    }
}
