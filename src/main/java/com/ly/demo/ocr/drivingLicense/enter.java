package com.ly.demo.ocr.drivingLicense;

import com.ly.demo.ocr.drivingLicense.utils.JdbcUtil;
import com.ly.demo.ocr.drivingLicense.utils.OCRUtil;

import java.sql.Date;
import java.util.Scanner;

/**
 * @author ：LY
 * @date ：2020/10/20 14:42
 * @description ：enter
 */

public class enter {
    private static final String PRE = "C:Users/Hjy/Desktop/car/";
    private static final String POST = ".jpg";

    public static void main(String[] args) {
        OCRUtil ocrUtil = new OCRUtil();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String license = scanner.nextLine();
            String url = PRE + license + POST;
            String no = ocrUtil.getNo(url);
            if (JdbcUtil.getEnterTimeByNo(no) != 0){
                System.out.println("系统显示该车辆已经进入，请确认");
                continue;
            }
            long entertime = System.currentTimeMillis();
            JdbcUtil.insertCar(no,entertime);
            System.out.println("欢迎" + no);
        }
    }
}