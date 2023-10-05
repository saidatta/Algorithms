package leetcode.tree.validate;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/
public class CountNodesEqualAverageSubtree {
    int count = 0;

    public int countAverageNodes(TreeNode root) {
        postOrder(root);
        return count;
    }

    private int[] postOrder(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0}; // {sum, count}
        }

        int[] left = postOrder(root.left);
        int[] right = postOrder(root.right);

        int subtreeSum = left[0] + right[0] + root.val;
        int subtreeCount = left[1] + right[1] + 1;

        if (root.val == subtreeSum / subtreeCount) {
            count++;
        }

        return new int[]{subtreeSum, subtreeCount};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4,
                new TreeNode(8, new TreeNode(0), new TreeNode(1)),
                new TreeNode(5, null, new TreeNode(6))
        );

        CountNodesEqualAverageSubtree solution = new CountNodesEqualAverageSubtree();
        int result = solution.countAverageNodes(root);

        System.out.println(result); // Expected output: 5
    }
}
