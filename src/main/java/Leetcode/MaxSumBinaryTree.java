package Leetcode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class MaxSumBinaryTree {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    // helper returns the max branch
    // plus current node's value
    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }

    public int maxPathSum2(TreeNode root) {
        int[] maxSum = {root.val};
        maxPathSum2(root, maxSum);
        return maxSum[0];
    }

    private int maxPathSum2(TreeNode root, int[] maxSum) {
        if (root == null)
            return 0;
        int left = maxPathSum2(root.left, maxSum);
        int right = maxPathSum2(root.right, maxSum);
        maxSum[0] = Math.max(maxSum[0], root.val); // only root
        maxSum[0] = Math.max(maxSum[0], root.val + left);  // root and left subtree
        maxSum[0] = Math.max(maxSum[0], root.val + right); // root and right subtree
        maxSum[0] = Math.max(maxSum[0], root.val + left + right); // root, left subtree, right subtree
        // 0 --> include root only i.e. exclude left and right because they are negative so can't increase the sum
        // left --> include left only
        // right --> include right only
        return root.val + Math.max(0, Math.max(left, right));
    }

    public static void main(String [] args) {
        MaxSumBinaryTree maxSumBinaryTree = new MaxSumBinaryTree();
        TreeNode treeNode = new TreeNode(-2);
        treeNode.left = new TreeNode(-1);
        treeNode.right =new TreeNode(-3);

        System.out.println(maxSumBinaryTree.maxPathSum2(treeNode));
    }
}
