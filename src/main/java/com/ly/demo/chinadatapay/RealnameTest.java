package com.ly.demo.chinadatapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：LY
 * @date ：2021/1/25 11:05
 * @description ：RealnameTest
 */

public class RealnameTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("http://api.chinadatapay.com/communication/personal/1882");
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        Map map = new HashMap();
        map.put("key","b5ab90b518737cb3501b1490cf010e26");
        map.put("name","何敬殷");
        map.put("idcard","342923199706244010");
        String s = JSON.toJSONString(map);
        StringEntity se = new StringEntity(s,"utf-8");
        httpPost.setEntity(se);
        try {
            HttpResponse res = httpClient.execute(httpPost, httpContext);
            String s1 = EntityUtils.toString(res.getEntity());
            System.out.println(s1);
            JSONObject jsonObject = JSON.parseObject(s1);
            System.out.println(jsonObject.getString("data"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
