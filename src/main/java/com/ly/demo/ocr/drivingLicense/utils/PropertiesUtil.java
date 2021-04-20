package com.ly.demo.ocr.drivingLicense.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ：LY
 * @date ：2020/10/20 15:06
 * @description ：PropertiesUtil
 */

public class PropertiesUtil {

    static Properties properties = new Properties();


    public PropertiesUtil() {
    }
    public static boolean loadFile(String fileName){
        try {
            properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static String getPropertyValue(String key){
        return properties.getProperty(key);
    }
}
