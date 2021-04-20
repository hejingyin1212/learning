package com.ly.demo.chinadatapay;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author ：LY
 * @date ：2021/1/25 13:48
 * @description ：DatapayUtils
 */

public class DatapayUtils {
    public static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object>
                                                                      params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        if (params == null || params.size() == 0) {
            return pairs;
        }
        for (Map.Entry<String, Object> param : params.entrySet()) {
            Object value = param.getValue();
            if (value instanceof String[]) {
                String[] values = (String[]) value;
                for (String v : values) {
                    pairs.add(new BasicNameValuePair(param.getKey(), v));
                }
            } else {
                if (value instanceof Integer) {
                    value = Integer.toString((Integer) value);
                } else if (value instanceof Long) {
                    value = Long.toString((Long) value);
                }
                pairs.add(new BasicNameValuePair(param.getKey(), (String) value));
            }
        }
        return pairs;
    }

}
