package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
public class MaximumDifferenceBetweenNodeAndAncestor {
    private int maxDiff = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return maxDiff;
    }

    private void dfs(TreeNode node, int minVal, int maxVal) {
        if (node == null) {
            return;
        }
        // Update the maximum difference
        maxDiff = Math.max(maxDiff, Math.abs(node.val - minVal));
        maxDiff = Math.max(maxDiff, Math.abs(node.val - maxVal));

        // Update min and max values
        minVal = Math.min(minVal, node.val);
        maxVal = Math.max(maxVal, node.val);

        // Traverse left and right children
        dfs(node.left, minVal, maxVal);
        dfs(node.right, minVal, maxVal);
    }

    public static void main(String[] args) {
        MaximumDifferenceBetweenNodeAndAncestor solution = new MaximumDifferenceBetweenNodeAndAncestor();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(13);
        System.out.println(solution.maxAncestorDiff(root)); // Output: 7
    }
}

