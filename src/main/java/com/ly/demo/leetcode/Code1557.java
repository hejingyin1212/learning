package com.ly.demo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ：LY
 * @date ：2021/2/2 11:33
 * @description ：Code1557
 */

class Code1557 {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List res = new ArrayList();
        Set set = new HashSet();
        for(List<Integer> edge : edges){
            Integer tar = edge.get(1);
            set.add(tar);
        }
        for (int i = 0;i<n;i++){
            if (!set.contains(i)){
                res.add(i);
            }
        }
        return res;
    }
}
