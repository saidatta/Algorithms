package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/#/description
 *
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

    public static void main(String [] args) {
        MaxPathSum maxPathSum = new MaxPathSum();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);

        System.out.println(maxPathSum.maxPathSum(root));
    }
}
