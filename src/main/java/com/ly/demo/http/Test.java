package com.ly.demo.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
 * @date ：2020/11/23 19:16
 * @description ：Test
 */

public class Test {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("http://test.guolinglaw.com/api/openapi/authenticationLogin");
        httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
        Map map = new HashMap();
        map.put("phoneOrEmail","17695656529");
        map.put("password","hejingyin24680");
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
        //HttpPost httpPost = new HttpPost("http://test.guolinglaw.com/web/property/getCaseList");
//        Case c = new Case();
//        c.setFirstFlag(1);
//        c.setSecondFlag(1);
//
//        String jsonString = JSON.toJSONString(c);
//
//        StringEntity entity = new StringEntity(jsonString, "UTF-8");
//
//        httpPost.setEntity(entity);
//
//        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 由客户端执行(发送)Post请求
//            response = httpClient.execute(httpPost);
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
