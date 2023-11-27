package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/maximum-average-subtree/description/
public class MaxAverageSubtree {
    private static class SubtreeData {
        int sum;
        int count;

        SubtreeData(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    private double maxAverage = Double.MIN_VALUE;

    public double maximumAverageSubtree(TreeNode root) {
        dfs(root);
        return maxAverage;
    }

    private SubtreeData dfs(TreeNode node) {
        if (node == null) {
            return new SubtreeData(0, 0);
        }

        SubtreeData leftData = dfs(node.left);
        SubtreeData rightData = dfs(node.right);

        int currentSum = node.val + leftData.sum + rightData.sum;
        int currentCount = 1 + leftData.count + rightData.count;
        double currentAverage = (double) currentSum / currentCount;

        maxAverage = Math.max(maxAverage, currentAverage);

        return new SubtreeData(currentSum, currentCount);
    }
}
