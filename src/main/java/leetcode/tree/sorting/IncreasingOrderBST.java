package leetcode.tree.sorting;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/increasing-order-search-tree/
public class IncreasingOrderBST {
    TreeNode prev = new TreeNode(0); // dummy node

    public TreeNode increasingBST(TreeNode root) {
        TreeNode newRoot = prev;  // Start from the dummy node
        inorder(root);
        return newRoot.right;  // Skip dummy node and return the new root
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        // No left child in the new tree
        node.left = null;
        // Attach current node to the right of previous node
        prev.right = node;
        // Move the pointer
        prev = node;

        inorder(node.right);
    }
}
