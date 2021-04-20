package com.ly.demo.interview;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Timer;

/**
 * @author ：LY
 * @date ：2021/3/3 16:47
 * @description ：Test
 */

public class Test {
    public static String replaceString(String a,String b,String c){
        TimeInterval timer = DateUtil.timer();
        timer.start();
        while (a.contains(b)){
            a = a.replace(b,c);
            System.out.println(a);
        }
        System.out.println("用时：" + timer.intervalMs() + "ms");
        return a;
    }

    public static void main(String[] args) {
        replaceString("aaabaaaabaaaabbaabbbaabaabababababab","ab","ba");
    }

}
