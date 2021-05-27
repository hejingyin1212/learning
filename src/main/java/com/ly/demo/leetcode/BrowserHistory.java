package com.ly.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：LY
 * @date ：2021/5/20 17:24
 * @description ：BrowserHistory   1472
 */

public class BrowserHistory {

    List<String> list = new ArrayList<>();
    int index;
    int fin;

    public BrowserHistory(String homepage) {
        list.add(homepage);
        index = 0;
    }

    public void visit(String url) {
        index++;
        if (index>=list.size()){
            list.add(index,url);
        }else {
            list.set(index,url);
        }
        fin = index;
    }

    public String back(int steps) {
        if (steps<=index){
            index -= steps;
        }else {
            index = 0;
        }
        return list.get(index);
    }

    public String forward(int steps) {
        if (steps+index<=fin){
            index +=steps;
        }else {
            index = fin;
        }
        return list.get(index);
    }


}
