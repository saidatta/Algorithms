package leetcode.tree;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 *
 * Created by venkatamunnangi on 24/11/16.
 */
public class BalancedBinaryTree {

    // o (n^2)
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        int hl = isBalancedHelper(root.left);
        int hr = isBalancedHelper(root.right);

        return (Math.abs(hl-hr) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * Getting height.
     * @param root
     * @return
     */
    int isBalancedHelper(TreeNode root) {
        if(root == null)
            return 0;

        int left = isBalancedHelper(root.left) + 1 ;
        int right = isBalancedHelper(root.right) + 1;

        return Math.max(left,right);
    }

    // o(n)
    public boolean isBalanced2(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private int checkBalance(TreeNode root) {
        // Base case
        if (root == null) return 0;

        int leftHeight = checkBalance(root.left);
        int rightHeight = checkBalance(root.right);

        // If left or right subtree is unbalanced, propagate -1 up
        if (leftHeight == -1 || rightHeight == -1) return -1;

        // If current tree is unbalanced, return -1
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        // Otherwise, return the actual height
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
