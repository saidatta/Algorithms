package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/longest-univalue-path/description/
 */
public class LongestUnivaluePath {
    private int maxPathLength = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        calculateMaxPath(root, -1);
        return maxPathLength - 1; // maxPathLength should be length in edges
    }

    private int calculateMaxPath(TreeNode node, int parentValue) {
        if (node == null) {
            return 0;
        }

        int left = calculateMaxPath(node.left, node.val);
        int right = calculateMaxPath(node.right, node.val);

        // update maximum path length if current path length is greater
        maxPathLength = Math.max(maxPathLength, left + right + 1);

        // if current node value is equal to parent node value, return the max of left or right path length + 1
        if (node.val == parentValue) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
