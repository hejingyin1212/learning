package com.ly.demo.designMode.builder;

/**
 * @author ：LY
 * @date ：2021/4/7 11:13
 * @description ：Director
 */

public class Director{
    //指挥装机人员组装电脑
    public void Construct(Builder builder){

        builder. BuildCPU();
        builder.BuildMainboard();
        builder. BuildHD();
    }
}



