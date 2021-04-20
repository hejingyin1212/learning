package com.ly.demo.threadlocal;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：LY
 * @date ：2021/2/25 14:58
 * @description ：CallService
 */

public class CallService {
    //用户编号创建器
    private static final AtomicInteger CREATOR = new AtomicInteger(1);
    //备选生日
    private static final LocalDate[] birthdays = {LocalDate.of(1988, 9, 11),
            LocalDate.of(1989, 11, 10),
            LocalDate.of(1990, 3, 7),
            LocalDate.of(1995, 7, 26),
            LocalDate.of(2000, 10, 1)
    };
    private static final int birthdayLength = birthdays.length;

    public static void main(String[] args) {
        UserService userService = new UserService();
        //同时10个调用
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                UserContext.set(initUser(Thread.currentThread().getName()));
                //进行调用
                userService.addUser();
            }, "callService-" + i).start();
        }
    }
    private static User initUser(String name){
        User user = new User();
        user.setUserId(CREATOR.getAndIncrement());
        user.setBirthday(birthdays[user.getUserId()%birthdayLength]);
        user.setName(name);
        return user;
    }

}
