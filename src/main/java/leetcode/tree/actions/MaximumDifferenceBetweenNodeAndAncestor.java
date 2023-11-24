package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
public class MaximumDifferenceBetweenNodeAndAncestor {
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode node, int curMax, int curMin) {
        if (node == null) {
            return curMax - curMin;
        }

        curMax = Math.max(curMax, node.val);
        curMin = Math.min(curMin, node.val);

        int left = dfs(node.left, curMax, curMin);
        int right = dfs(node.right, curMax, curMin);

        return Math.max(left, right);
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

