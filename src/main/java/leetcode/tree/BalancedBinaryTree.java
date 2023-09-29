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

    //  In this bottom up approach, each node in the tree only need to be accessed once.
    // Thus the time complexity is O(N), better than the first solution.
    public boolean isBalanced2(TreeNode root) {
        return getHeightHelper(root) != Integer.MIN_VALUE;
    }

    private int getHeightHelper(TreeNode root) {
        if(root == null) {
            return -1;
        }

        int leftHeight = getHeightHelper(root.left);
        if(leftHeight == Integer.MIN_VALUE) {
            return leftHeight;
        }

        int rightHeight = getHeightHelper(root.right);
        if(rightHeight == Integer.MIN_VALUE) {
            return rightHeight;
        }

        if(Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
