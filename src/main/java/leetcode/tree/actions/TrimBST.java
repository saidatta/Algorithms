package leetcode.tree.actions;

// https://leetcode.com/problems/trim-a-binary-search-tree/

import leetcode.tree.util.TreeNode;

public class TrimBST {
    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;

        // If root's value is greater than high, trim the right subtree, by going to left
        if (root.val > high) return trimBST(root.left, low, high);

        // If root's value is less than low, trim the left subtree, by going to right
        if (root.val < low) return trimBST(root.right, low, high);

        // Otherwise, trim both left and right subtrees
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3, new TreeNode(0, null, new TreeNode(2, new TreeNode(1), null)), new TreeNode(4));
        int low = 1, high = 3;
        TreeNode trimmed = trimBST(root, low, high);

        printInorder(trimmed);  // Expected output: 1 2 3
    }

    // Method to print the tree - for testing purposes
    public static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

}
