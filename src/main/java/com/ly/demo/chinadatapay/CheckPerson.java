package com.ly.demo.chinadatapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：LY
 * @date ：2021/1/25 11:12
 * @description ：CheckPerson
 */

public class CheckPerson {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("http://api1.chinadatapay.com/trade/user/9204");
        Map map = new HashMap();
        map.put("key","285e324f64cdd2c9f5d81d554b39eef2");
        map.put("imageId","a8df08b59a9748e1a4d3e1a3877b008b");
        try {
            ArrayList<NameValuePair> pairs = DatapayUtils.covertParams2NVPS(map);
            httpPost.setEntity(new UrlEncodedFormEntity(pairs,"UTF-8"));
            HttpResponse res = httpClient.execute(httpPost, httpContext);
            String s1 = EntityUtils.toString(res.getEntity());
            System.out.println(s1);
            JSONObject jsonObject = JSON.parseObject(s1);
            System.out.println(jsonObject.getString("data"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
