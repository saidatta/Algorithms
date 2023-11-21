package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

public class CountNodesEqualToSumOfDescendants {

    public int equalToDescendants(TreeNode root) {
        int[] count = new int[1];
        sumOfDescendants(root, count);
        return count[0];
    }

    private int sumOfDescendants(TreeNode node, int[] count) {
        if (node == null) {
            return 0;
        }
        int leftSum = sumOfDescendants(node.left, count);
        int rightSum = sumOfDescendants(node.right, count);
        if (node.val == leftSum + rightSum) {
            count[0]++;
        }
        return node.val + leftSum + rightSum;
    }

    public static void main(String[] args) {
        CountNodesEqualToSumOfDescendants solution = new CountNodesEqualToSumOfDescendants();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        System.out.println(solution.equalToDescendants(root)); // Output: 2
    }
}

