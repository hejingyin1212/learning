package com.ly.demo.leetcode;

import com.ly.demo.leetcode.entity.TreeNode;

/**
 * @author ：LY
 * @date ：2021/5/24 15:20
 * @description ：Code543
 */

public class Code543 {
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        int l = depth(treeNode.left);
        int r = depth(treeNode.right);
        ans = Math.max(ans, l + r + 1);
        return Math.max(l, r) + 1;
    }
}
