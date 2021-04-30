package com.ly.demo.ocr.ocr4;

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
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/business_license";
        try {
            // 本地文件路径
            String filePath = "F:\\yyzz1.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        OcrTest.businessLicense();
        String[] split = "https://e-contract-gl-dev.oss-cn-shanghai.aliyuncs.com/20210428EavqYp.png".split("/");
        Arrays.stream(split).forEach(s -> {
            System.out.println(s);
        });
    }
}
