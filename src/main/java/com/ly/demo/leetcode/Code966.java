package com.ly.demo.leetcode;

import com.ly.M;
import org.bytedeco.javacpp.presets.opencv_core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：LY
 * @date ：2021/4/27 13:37
 * @description ：Code966
 */

public class Code966 {

    public String[] spellchecker(String[] wordlist, String[] queries) {

        List<String> checks = Arrays.stream(wordlist).map(str -> str.toUpperCase().replaceAll("[A,E,I,O,U]", "A")).collect(Collectors.toList());
        String[] results = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int j = 0; j < checks.size(); j++) {
                if (checks.get(j).equals(queries[i].toUpperCase().replaceAll("[A,E,I,O,U]", "A"))){
                    results[i] = wordlist[j];
                    break;
                }
            }
            if (Objects.isNull(results[i])){
                results[i] = "";
            }
        }
        return results;
    }

    public static void main(String[] args) {
        String[] strings = new String[3];
        System.out.println(Objects.isNull(strings[2]));
    }


}
