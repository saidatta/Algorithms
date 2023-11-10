package leetcode.tree.validate;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

public class CheckCompleteBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean mustBeLeaf = false; // Flag to indicate whether we are at the last level.

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            if (mustBeLeaf && (currentNode.left != null || currentNode.right != null)) {
                return false; // Found a non-leaf node after an incomplete node.
            }

            if (currentNode.left == null && currentNode.right != null) {
                return false; // Right child is present but left child is not.
            }

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            } else {
                mustBeLeaf = true; // If left child is missing, all other nodes must be leaf nodes.
            }

            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            } else {
                mustBeLeaf = true; // If right child is missing, all other nodes must be leaf nodes.
            }
        }

        return true; // Passed all checks, tree is complete.
    }

    public static void main(String[] args) {
        // Test cases
        CheckCompleteBinaryTree cbt = new CheckCompleteBinaryTree();

        // Example 1:
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        System.out.println("Example 1: " + cbt.isCompleteTree(root1)); // Output: true

        // Example 2:
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(7);
        System.out.println("Example 2: " + cbt.isCompleteTree(root2)); // Output: false
    }
}

