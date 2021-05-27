package com.ly.demo.leetcode;

import com.ly.demo.leetcode.entity.TreeNode;

import java.util.Objects;

/**
 * @author ：LY
 * @date ：2021/5/13 13:06
 * @description ：Code951
 */

public class Code951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (Objects.isNull(root1)&&Objects.isNull(root2)){
            return true;
        }
        if (Objects.isNull(root1)||Objects.isNull(root2)){
            return false;
        }
        if (root1.val != root2.val){
            return false;
        }else {
            if (flipEquiv(root1.left,root2.left)&&flipEquiv(root1.right,root2.right)){
                return true;
            }else if (flipEquiv(root1.left,root2.right)&&flipEquiv(root1.right,root2.left)){
                return true;
            }else {
                return false;
            }
        }

    }

}

