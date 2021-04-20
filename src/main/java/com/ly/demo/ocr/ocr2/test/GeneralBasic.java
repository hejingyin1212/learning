package com.ly.demo.ocr.ocr2.test;

/**
 * @author ：LY
 * @date ：2020/9/29 14:33
 * @description ：GeneralBasic
 */

import com.ly.demo.ocr.ocr2.tools.Base64Util;
import com.ly.demo.ocr.ocr2.tools.FileUtil;
import com.ly.demo.ocr.ocr2.tools.HttpUtil;

import java.net.URLEncoder;

/**
 * 通用文字识别
 */

public class GeneralBasic {
    public static String generalBasic()
    {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
//        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";


        try
        {
            // 本地文件路径
            String filePath = "C:\\Users\\Hjy\\Desktop\\ui1.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            AuthService authService = new AuthService();
            String token = authService.getAuth();
            System.out.println("token = " + token);
            String accessToken = token;

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        System.out.println("已开始...");
        System.out.println(GeneralBasic.generalBasic());
//        JSONObject object = (JSONObject)JSONObject.parse(GeneralBasic.generalBasic());
//        String code = ((JSONObject)((JSONObject)object.get("words_result")).get("单位名称")).get("words").toString();
//        String code2 = ((JSONObject)((JSONObject)object.get("words_result")).get("成立日期")).get("words").toString();
//        String code3 = ((JSONObject)((JSONObject)object.get("words_result")).get("经营范围")).get("words").toString();
//        String code4 = ((JSONObject)((JSONObject)object.get("words_result")).get("法人")).get("words").toString();
//        String code5 = ((JSONObject)((JSONObject)object.get("words_result")).get("注册资本")).get("words").toString();
//        String code6 = ((JSONObject)((JSONObject)object.get("words_result")).get("地址")).get("words").toString();
//
//        System.out.println(code);
//        System.out.println(code2);
//        System.out.println(code3);
//        System.out.println(code4);
//        System.out.println(code5);
//        System.out.println(code6);
//        String t ="https://e-contract-gl-dev.oss-cn-shanghai.aliyuncs.com/20201009WmVhEq.jpg";
//        String[] strings =  t.split("/");
//        System.out.println(strings[3]);
    }
}
