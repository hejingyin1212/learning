package com.ly.test;

import javax.activation.MailcapCommandMap;

/**
 * @author ：LY
 * @date ：2021/2/26 10:58
 * @description ：Test
 */

public class Test {
    static int i;
    static class Console {
        private void out(){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Console[] consoles  = new Console[11];
        for (i=0;i<10;i++){
            consoles[i] = new Console();
        }
        for (int j = 0 ; j<10 ; j++){
            consoles[j].out();
        }
    }

}
