package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class MinAbsoluteDiffBST {
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    /**
     * The most common idea is to first inOrder traverse the tree and compare the delta between each of the adjacent values.
     * It's guaranteed to have the correct answer because it is a BST thus inOrder traversal values are sorted.
     * In-Order traverse, time complexity O(N), space complexity O(1).
     *
      * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }

        getMinimumDifference(root.left);

        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;

        getMinimumDifference(root.right);

        return min;
    }

    public int getMinimumDifferenceIterative(TreeNode root) {
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null) {
                    min = Math.min(min, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return min;
    }
}
