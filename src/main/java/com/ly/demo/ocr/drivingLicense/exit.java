package com.ly.demo.ocr.drivingLicense;

import com.ly.demo.ocr.drivingLicense.utils.JdbcUtil;
import com.ly.demo.ocr.drivingLicense.utils.OCRUtil;

import java.util.Scanner;

/**
 * @author ：LY
 * @date ：2020/10/20 14:42
 * @description ：exit
 */

public class exit {
    private static final String PRE = "C:Users/Hjy/Desktop/car/";
    private static final String POST = ".jpg";

    public static void main(String[] args) {
        OCRUtil ocrUtil = new OCRUtil();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String license = scanner.nextLine();
            String url = PRE + license + POST;
            String no = ocrUtil.getNo(url);
            long exittime = System.currentTimeMillis();
            long entertime = JdbcUtil.getEnterTimeByNo(no);
            if (entertime == 0){
                System.out.println("记录显示该车牌未进入，请进行确认");
                continue;
            }
            float value = (exittime-entertime)/1000/60;
            int stayHour = (int) Math.ceil(value);
            System.out.println("车牌："+ no + "累计时长：" + stayHour + ",请支付" + stayHour*6 + "元");
            JdbcUtil.deleteCar(no);
            boolean flag = true;
//            while(flag){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("累计时长：" + stayHour + ",请支付" + stayHour*6 + "元");
//                flag = getFlag();
//            }
        }
    }

    private static boolean getFlag(){
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if(i == 0){
            return true;
        }
        return false;
    }
}
