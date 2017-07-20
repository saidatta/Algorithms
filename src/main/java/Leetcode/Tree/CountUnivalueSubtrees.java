package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/count-univalue-subtrees/#/description
 *
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value
 *
 * Created by venkatamunnangi on 3/14/17.
 */
public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        int[] count = new int[1]; // since primitives are not passed by reference.
        helper(root, count);
        return count[0];
    }

    private boolean helper(TreeNode node, int[] count) {
        if (node == null) {
            return true;
        }

        boolean left = helper(node.left, count);
        boolean right = helper(node.right, count);
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count[0]++;
            return true;
        }

        return false;
    }
}
