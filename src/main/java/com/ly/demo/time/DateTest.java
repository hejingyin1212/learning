package com.ly.demo.time;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ：LY
 * @date ：2021/3/10 11:04
 * @description ：DateTest
 */

public class DateTest {
    public static void main(String[] args) {
        //1615345622245
        //1583809622245
        Date date = new Date(2020, 3, 12, 12, 30, 25);
        Date date2 = new Date(2021, 3, 12, 12, 30, 25);
        long diffInMillies = Math.abs(date2.getTime() - date.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diff);
    }
}
