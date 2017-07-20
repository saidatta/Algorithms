package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/#/description
 *
 * Created by venkatamunnangi on 5/8/17.
 */
public class BTLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(dfs(root.left, 1, root.val), dfs(root.right, 1, root.val));
        }
    }

    private int dfs(TreeNode root, int count, int val) {
        if (root == null) {
            return count;
        }
        count = (root.val - val == 1) ? count + 1 : 1;
        int left = dfs(root.left, count, root.val);
        int right = dfs(root.right, count, root.val);
        return Math.max(Math.max(left, right), count);
    }
}
