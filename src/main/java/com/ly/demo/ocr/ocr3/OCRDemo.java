package com.ly.demo.ocr.ocr3;

import org.json.JSONObject;
import com.baidu.aip.ocr.AipOcr;

import java.util.HashMap;

/**
 * @author ：LY
 * @date ：2020/10/12 13:13
 * @description ：OCRUtil
 */

public class OCRDemo {
    //设置APPID/AK/SK
    public static final String APP_ID = "22796234";
    public static final String API_KEY = "VQ04nLY8SEie39SsfVEHf4iH";
    public static final String SECRET_KEY = "QkMHlrhuuvp54GAxl95zFZsKdDdAfw58";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

//        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        // 传入可选参数调用接口
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
        String url = "C:\\Users\\Hjy\\Desktop\\1.png";
//         通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl(url, options);
//        JSONObject res = client.businessLicense(url,options);
        JSONObject res = client.basicAccurateGeneral(url, options);
        try {
            System.out.println(res.toString());
//            System.out.println(res.getJSONObject("words_result").getJSONObject("法人").getString("words"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
