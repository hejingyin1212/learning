package com.ly.demo.leetcode;

/**
 * @author ：LY
 * @date ：2021/5/20 17:05
 * @description ：Code1518
 */

public class Code1518 {
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = numBottles;
        while (numBottles / numExchange > 0) {
            res += numBottles / numExchange;
            numBottles = numBottles / numExchange + numBottles % numExchange;
        }
        return res;
    }
    //数学
    public int numWaterBottles2(int numBottles, int numExchange) {
        return numBottles >= numExchange ? (numBottles - numExchange) / (numExchange - 1) + 1 + numBottles : numBottles;
    }
}
