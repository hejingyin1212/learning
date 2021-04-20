package com.ly.demo.code;

import com.alibaba.fastjson.JSONArray;
import com.ly.entity.TCase;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.net.www.http.HttpClient;

/**
 * @author ：LY
 * @date ：2020/12/6 14:19
 * @description ：base64
 */

public class base64 {
    public static void main(String[] args) throws Exception {
//        String in = "379";
//        BASE64Encoder encoder = new BASE64Encoder();
//        String encode = encoder.encode(in.getBytes());
//        BASE64Decoder decoder = new BASE64Decoder();
//        String decode = new String(decoder.decodeBuffer("MmZhNDg1ZWItZWQwNi00YjBjLTg2ZTctNmJmOWJkNjk1OGY5"));
//        System.out.println("加密后：" + encode);
//        System.out.println("解密后：" + decode);
//        String encode =  new String(Base64.encodeBase64("0fe464be-2634-40c0-9f94-f972e67d1796".getBytes()));
//
//        System.out.println(encode);
//        TCase t1 = new TCase();
//        TCase t2 = new TCase();
//        t1.setId(1l);
//        t2.setId(1l);
//        System.out.println(t2.getId().equals(t1.getId()) );

//        HttpPost httpPost=null;
//        CloseableHttpClient client=null;
//        JSONObject jsonParam=null;
//        String responseContent=null;
        TCase tCase = new TCase();
        tCase.setDescription("2342");
        tCase.setText("dfggsfd");
        Object o = JSONArray.toJSON(tCase);
        String s = o.toString();
        System.out.println(s);

    }
}
