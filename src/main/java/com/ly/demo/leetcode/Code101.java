package com.ly.demo.leetcode;

import com.ly.demo.leetcode.entity.TreeNode;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：LY
 * @date ：2021/5/20 14:01
 * @description ：Code101
 */

public class Code101 {
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }



}
