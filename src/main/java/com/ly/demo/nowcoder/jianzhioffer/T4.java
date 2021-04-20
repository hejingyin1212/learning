package com.ly.demo.nowcoder.jianzhioffer;

import com.ly.demo.nowcoder.jianzhioffer.entity.TreeNode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

/**
 * @author ：LY
 * @date ：2021/3/15 16:11
 * @description ：T4
 */

public class T4 {
//    public static TreeNode reConstructBinaryTree(int [] pre, int [] in) {
//        return createTree(pre,in,0,pre.length-1,0,in.length-1);
//    }
    public static TreeNode createTree(int [] pre, int [] in, int i,int j,int k,int l){
        if (j-i < 0){
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[i]);
        if (j-i == 0){
            return treeNode;
        }
        int mid  = 0;
        for (int a = k ; a < l ; a++){
            if (in[a] == pre[i]){
                mid = a;
                break;
            }
        }
        treeNode.left = createTree(pre, in, i+1, i+mid-k, k, mid-1);
        treeNode.right = createTree(pre, in, i+mid-k+1,j,mid+1,l);
        return treeNode;
    }

    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
    public static void preOrderTraverse1(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + "->");
            preOrderTraverse1(root.left);
            preOrderTraverse1(root.right);
        }
    }
    public static void inOrderTraverse(TreeNode root) {
        if (root != null) {
            inOrderTraverse(root.left);
            System.out.print(root.val + "->");
            inOrderTraverse(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = reConstructBinaryTree(new int[]{1, 2, 3, 4, 5, 6}, new int[]{4, 2, 1, 5, 3, 6});
        preOrderTraverse1(treeNode);
        inOrderTraverse(treeNode);
    }
}
