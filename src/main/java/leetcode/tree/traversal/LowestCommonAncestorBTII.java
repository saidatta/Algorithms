package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/
public class LowestCommonAncestorBTII {
    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        util(root, p, q);
        return ans;
    }

    private TreeNode util(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = util(root.left, p, q);
        TreeNode right = util(root.right, p, q);

        if (root == p || root == q) {
            if (left != null || right != null) {
                ans = root;
                return null;
            }
            return root;
        }

        if (left != null && right != null) {
            ans = root;
            return null;
        }
        return left != null ? left : right;
    }
}
