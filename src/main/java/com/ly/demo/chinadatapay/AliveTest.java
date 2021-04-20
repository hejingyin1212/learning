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
 * @date ：2021/1/25 11:04
 * @description ：AliveTest
 */

public class AliveTest {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("http://api.chinadatapay.com/trade/user/9204");
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        Map map = new HashMap();
        map.put("key","285e324f64cdd2c9f5d81d554b39eef2");
        map.put("imageId","224ee3148b7a472e96a94edacf296f48");
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
