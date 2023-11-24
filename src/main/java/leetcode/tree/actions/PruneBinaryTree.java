package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/binary-tree-pruning/description/
public class PruneBinaryTree {
    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

    private boolean containsOne(TreeNode node) {
        if (node == null) {
            return false;
        }

        boolean leftContainsOne = containsOne(node.left);
        boolean rightContainsOne = containsOne(node.right);

        // pruning
        if (!leftContainsOne) {
            node.left = null;
        }

        if (!rightContainsOne) {
            node.right = null;
        }

        // if node.val == 0, but left or right subtree has 1. no need to prune.
        // just pass through current subtree statuses if node.val == 0.
        return node.val == 1 || leftContainsOne || rightContainsOne;
    }
}
