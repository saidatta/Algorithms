package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Created by venkatamunnangi on 10/7/19.
 */
public class LowestCommonAncestorBT {

    //post order travasal. once we find non-null values
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        } else {
            if (root == p) {
                return p;
            }
            if (root == q) {
                return q;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.left, p, q);

            if (left != null && right != null) {
                return root;
            }
            return left == null ? right : left;
        }
    }
}
