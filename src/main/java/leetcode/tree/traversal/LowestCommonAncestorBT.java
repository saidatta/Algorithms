package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Created by venkatamunnangi on 10/7/19.
 */
public class LowestCommonAncestorBT {

    // post order traversal. once we find non-null values
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

    // post order traversal. once we find non-null values
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // If the current node is either p or q, then this node is one of the ancestors.
        if (root == p || root == q) return root;

        // Check for p and q in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If p and q are found in left and right subtrees of the current node, then it's the LCA.
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, if left subtree has a node then return left (or) return right.
        return left != null ? left : right;
    }
}
