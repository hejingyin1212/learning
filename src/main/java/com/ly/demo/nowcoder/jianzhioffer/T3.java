package com.ly.demo.nowcoder.jianzhioffer;

import com.ly.demo.nowcoder.jianzhioffer.entity.ListNode;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author ：LY
 * @date ：2021/3/15 15:01
 * @description ：T3
 */

public class T3 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack stack = new Stack();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        ArrayList arrayList = new ArrayList();
        while (!stack.empty()){
            arrayList.add(stack.pop());
        }
        return arrayList;
    }

}
