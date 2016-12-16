package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * Created by venkatamunnangi on 24/11/16.
 */
public class MaxPathSum {

    int maxPath = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxPath;
    }

    public int maxPathSumHelper(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int mLeft = Math.max(maxPathSumHelper(root.left), 0);
        int mRight = Math.max(maxPathSumHelper(root.right), 0);



        maxPath = Math.max(maxPath, mLeft + mRight + root.val);

        return Math.max(mLeft, mRight) + root.val;

    }
}
