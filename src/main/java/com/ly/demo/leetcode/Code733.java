package com.ly.demo.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ：LY
 * @date ：2021/5/6 10:12
 * @description ：Code733
 */

public class Code733 {


    public static void main(String[] args) {
        Code733 code733 = new Code733();
        System.out.println(code733.floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2));
    }

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int colorTmp = image[sr][sc];
        if (newColor == colorTmp) {
            return image;
        }
        int m = image.length, n = image[0].length;
        Stack<int[]> stack = new Stack<int[]>();
        stack.push(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!stack.empty()) {
            int[] pop = stack.pop();
            int x = pop[0], y = pop[1];
            System.out.println(x + "," + y);
            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];
                if (xx >= 0 && yy >= 0 && xx < m && yy < n && image[xx][yy] == colorTmp) {
                    stack.push(new int[]{xx, yy});
                    image[xx][yy] = newColor;
                }
            }
        }
        return image;
    }

}
