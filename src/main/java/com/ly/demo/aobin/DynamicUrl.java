package com.ly.demo.aobin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：LY
 * @date ：2021/3/23 16:11
 * @description ：DynamicUrl
 */

public class DynamicUrl {
    public static String getKuaiShouSign(String url){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(url.getBytes("UTF-8"));
            byte[] bytes = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer();
            for (int offset = 0 ; offset < bytes.length ; offset++){
                i = bytes[offset];
                if (i < 0){i+=256;}
                if (i < 16){stringBuffer.append("0");}
                stringBuffer.append(Integer.toHexString(i));
                url = stringBuffer.toString();
                System.out.println(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
    static ArrayList list = new ArrayList();
    public static void main(String[] args) {
//        getKuaiShouSign("/hejingyin3678");
        ThreadLocal t;
//        Thread

    }
}
