package com.ly.demo.ocr.ocr1;

/**
 * @author ：LY
 * @date ：2020/9/28 17:03
 * @description ：OCRTest
 */


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class OCRTest {

    public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "xFpObOpIU3QCcFqUsSFHfK0K");
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Hjy\\Desktop\\yyzz.jpg");
        String imageBase = BASE64.encodeImgageToBase64(file);
        imageBase = imageBase.replaceAll("\r\n", "");
        imageBase = imageBase.replaceAll("\\+", "%2B");
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
        String httpArg = "fromdevice=pc&clientip=10.10.10.0&detecttype=LocateRecognize&languagetype=CHN_ENG&imagetype=1&image=" + imageBase;
        String jsonResult = request(httpUrl, httpArg);
        System.out.println("返回的结果--------->" + jsonResult);
    }


}



