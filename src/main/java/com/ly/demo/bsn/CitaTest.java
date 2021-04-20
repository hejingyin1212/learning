package com.ly.demo.bsn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsnbase.sdk.client.cita.CitaClient;
import com.bsnbase.sdk.entity.config.Config;
import com.bsnbase.sdk.entity.req.cita.ReqKeyEscrow;
import com.bsnbase.sdk.entity.res.cita.ResKeyEscrow;
import com.bsnbase.sdk.util.common.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LY
 * @date ：2020/11/24 11:05
 * @description ：CitaTest
 */

public class CitaTest {
    public static void initConfig() throws IOException {
        Config config = new Config();
        config.setAppCode("app0001202011101338410817538");
        config.setUserCode("USER0001202009111431416437424");
        config.setApi("https://hangzhoutelecomnode.bsngate.com:17602");
        config.setPrk(Common.readLocalFile("D:\\PCNGateway-Java-SDK-master\\src\\main\\resources\\cert\\private_key.pem"));
        config.setPuk(Common.readLocalFile("D:\\PCNGateway-Java-SDK-master\\src\\main\\resources\\cert\\public_cert.pem"));
        config.setMspDir("D:/Test");
        //config.setTestServerIdn(true);
        config.initConfig(config);
    }

    public static void main(String[] args) {
        try {
            initConfig();
            ReqKeyEscrow reqkey = new ReqKeyEscrow();
            reqkey.setContractName("CitaBsnBaseContract");
            reqkey.setFuncName("retrieve");
            List<Object> list=new ArrayList<>();
            list.add(Common.getByte32("t".getBytes()));
            //list.add(Common.getByte16("hjy".getBytes()));
            reqkey.setFuncParam(JSON.toJSONString(list));
            System.out.println(JSON.toJSONString(list));
            reqkey.setUserId("guolinglaw");
            ResKeyEscrow resKeyEscrow = CitaClient.reqChainCode(reqkey);
            System.out.println(JSONObject.toJSONString(resKeyEscrow, SerializerFeature.PrettyFormat));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
