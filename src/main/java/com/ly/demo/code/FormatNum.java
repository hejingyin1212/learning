package com.ly.demo.code;

import com.google.protobuf.TextFormat;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;

/**
 * @author ：LY
 * @date ：2020/12/8 14:51
 * @description ：FormatNum
 */

public class FormatNum {
    public static void main(String[] args) {
        String format = String.format("%05d", 379999l);
        System.out.println(format);
        System.out.println(Long.parseLong(format));
        System.out.println("AD-00279".split("-")[1]);
        System.out.println(StringUtils.isNumeric("00237"));
        System.out.println("32525235".split("-").length);
    }
}
