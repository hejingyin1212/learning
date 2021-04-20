package com.ly.demo.ocr.drivingLicense.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author ：LY
 * @date ：2020/10/12 13:13
 * @description ：OCRUtil
 */

public class OCRUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "22796234";
    public static final String API_KEY = "VQ04nLY8SEie39SsfVEHf4iH";
    public static final String SECRET_KEY = "QkMHlrhuuvp54GAxl95zFZsKdDdAfw58";

    public String getNo(String url) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        JSONObject res = client.plateLicense(url,options);
        try {
            return res.getJSONObject("words_result").getString("number");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
