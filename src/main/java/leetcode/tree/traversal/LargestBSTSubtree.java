package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

public class LargestBSTSubtree {

    // Global variable to track the maximum number of nodes in a BST
    private int maxNodes = 0;

    // Main function to get the largest BST subtree
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverseAndCompute(root);
        return maxNodes;
    }

    /**
     * Helper function to traverse the tree and compute information.
     * It uses post-order traversal to determine the BST-ness of subtrees
     * rooted at each node.
     *
     * @param root The current root of the subtree being examined
     * @return Info Object containing metadata about the subtree.
     */
    private Info traverseAndCompute(TreeNode root) {
        if (root == null) {
            return new Info(0, Integer.MAX_VALUE, Integer.MIN_VALUE, true);
        }

        Info leftInfo = traverseAndCompute(root.left);
        Info rightInfo = traverseAndCompute(root.right);

        if (leftInfo.isBST && rightInfo.isBST && root.val > leftInfo.maxVal && root.val < rightInfo.minVal) {
            int currentNodes = leftInfo.nodes + rightInfo.nodes + 1;
            maxNodes = Math.max(maxNodes, currentNodes);
            return new Info(currentNodes, Math.min(root.val, leftInfo.minVal), Math.max(root.val, rightInfo.maxVal), true);
        } else {
            return new Info(0, 0, 0, false);
        }
    }

    /**
     * Helper class to store metadata about a subtree.
     */
    static class Info {
        int nodes;       // Number of nodes in the subtree
        int minVal;      // Minimum value in the subtree
        int maxVal;      // Maximum value in the subtree
        boolean isBST;   // Flag indicating if the subtree is a BST

        Info(int n, int min, int max, boolean bst) {
            nodes = n;
            minVal = min;
            maxVal = max;
            isBST = bst;
        }
    }
}
