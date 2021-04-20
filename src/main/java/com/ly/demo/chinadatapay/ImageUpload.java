package com.ly.demo.chinadatapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.entity.*;

/**
 * @author ：LY
 * @date ：2021/1/25 11:20
 * @description ：ImageUpload
 */

public class ImageUpload {
    public static String uploadImage(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("https://file.chinadatapay.com/img/upload?appkey=b5ab90b518737cb3501b1490cf010e26");
        httpPost.addHeader("key", "multipart/form-data; charset=utf-8");
        FileBody fileBody = new FileBody(new File("C:/Users/Hjy/Desktop/Test/video/1.mp4"));
        MultipartEntity entity = new MultipartEntity();
        entity.addPart("data", fileBody);
        httpPost.setEntity(entity);
        try {
            HttpResponse res = httpClient.execute(httpPost, httpContext);
            String s1 = EntityUtils.toString(res.getEntity());
            System.out.println(s1);
            JSONObject jsonObject = JSON.parseObject(s1);
            System.out.println(jsonObject.getString("data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        uploadImage();
    }
}
