package leetcode.tree;

import leetcode.tree.util.TreeNode;

public class RootEqualsSumChildren {
    public boolean isSumTree(TreeNode root) {
        if (root == null || root.left == null || root.right == null) {
            return false;
        }

        return root.val == root.left.val + root.right.val;
    }
}
