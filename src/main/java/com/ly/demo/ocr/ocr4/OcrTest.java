package com.ly.demo.ocr.ocr4;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.demo.ocr.utils.Base64Util;
import com.ly.demo.ocr.utils.FileUtil;
import com.ly.demo.ocr.utils.HttpUtil;

import java.net.URLEncoder;
import java.util.Arrays;

/**
 * @author ：LY
 * @date ：2021/4/28 11:13
 * @description ：OcrTest
 */

public class OcrTest {
    public static String businessLicense() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
//        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";
        try {
            // 本地文件路径
            String filePath = "C:\\Users\\Hjy\\Desktop\\lzsq.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray arr = jsonObject.getJSONArray("words_result");
            StringBuffer buffer = new StringBuffer();
            for (int i = 0 ; i<arr.size();i++){
                JSONObject object = (JSONObject) arr.get(i);
                buffer.append(object.getString("words"));
            }
            System.out.println(buffer.toString());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        OcrTest.businessLicense();
    }
}
