package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * Created by venkatamunnangi on 24/11/16.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        int hl = isBalancedHelper(root.left);
        int hr = isBalancedHelper(root.right);

        return (Math.abs(hl-hr) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    int isBalancedHelper(TreeNode root) {
        if(root == null)
            return 0;

        int left = isBalancedHelper(root.left) + 1 ;
        int right = isBalancedHelper(root.right) + 1;

        return Math.max(left,right);
    }
}
