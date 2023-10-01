package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class MaxDepthBT {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0; // Base case

        int leftDepth = maxDepth(root.left);  // Depth of left subtree
        int rightDepth = maxDepth(root.right);  // Depth of right subtree

        return 1 + Math.max(leftDepth, rightDepth);
    }
}
