package com.ly.demo.leetcode.entity;

/**
 * @author ：LY
 * @date ：2021/5/13 13:08
 * @description ：TreeNode
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
