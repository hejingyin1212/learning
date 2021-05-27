package com.ly.demo.code;

/**
 * @author ：LY
 * @date ：2021/5/11 16:28
 * @description ：Code1706
 */

public class Code1706 {
    public int[] findBall(int[][] grid) {
        int[] res = new int[grid[0].length];
        for(int i = 0;i<res.length;i++){
            int x = i;
            for (int j = 0;j<grid.length;j++){
                if (grid[j][x] == 1){
                    if (x>=grid[0].length-1||grid[j][x+1] == -1){
                        res[i] = -1;
                        break;
                    }else {
                        x++;
                        if(j >=grid.length-1){
                            res[i] = x;
                            break;
                        }
                    }
                }else {
                    if (x<=0||grid[j][x-1] == 1){
                        res[i] = -1;
                        break;
                    }else {
                        x--;
                        if(j >=grid.length-1){
                            res[i] = x;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
