package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/#/description
 *
 * Created by venkatamunnangi on 5/8/17.
 */
public class BTLongestConsecutiveSequence {
    private int maxLength = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0, root.val);
        return maxLength;
    }

    private void dfs(TreeNode node, int currentLength, int target) {
        if (node == null) {
            return;
        }
        if (node.val == target) {
            currentLength++;
        } else {
            currentLength = 1;
        }
        maxLength = Math.max(maxLength, currentLength);
        dfs(node.left, currentLength, node.val + 1);
        dfs(node.right, currentLength, node.val + 1);
    }

    public static void main(String[] args) {
        BTLongestConsecutiveSequence solution = new BTLongestConsecutiveSequence();
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(5);
        System.out.println(solution.longestConsecutive(root1)); // Output: 3

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.left = new TreeNode(2);
        root2.right.left.left = new TreeNode(1);
        System.out.println(solution.longestConsecutive(root2)); // Output: 2
    }
}
