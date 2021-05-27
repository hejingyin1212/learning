package com.ly.demo.leetcode;

import com.ly.demo.leetcode.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：LY
 * @date ：2021/5/20 13:12
 * @description ：leetcode-919
 */

public class CBTInserter {

    TreeNode root;
    Queue<TreeNode> queue = new ConcurrentLinkedQueue();

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.offer(root);
        init();
    }

    private void init() {
        TreeNode h = queue.peek();
        if (h.right != null){
            queue.offer(h.left);
            queue.offer(h.right);
            queue.poll();
            init();
        }else {
            if (h.left != null){
                queue.offer(h.left);
            }
        }
    }

    public int insert(int v) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = v;

        queue.offer(treeNode);
        TreeNode next = queue.peek();
        int res = next.val;
        if (next.left == null) {
            next.left = treeNode;
        } else {
            next.right = treeNode;
            queue.poll();
        }
        return res;
    }

    public TreeNode get_root() {
        return root;
    }
}
