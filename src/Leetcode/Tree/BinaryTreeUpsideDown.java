package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * Created by venkatamunnangi on 12/15/16.
 */
public class BinaryTreeUpsideDown {

    /// This problem has a pattern of a curve.

    /**
     *      root
     *     /    \
     *     L     R  ->    L2   root            L2
     *                   /  \ /    \  ->     /   \
     *    / \           R2   L     R        R2    L
     *   L2  R2                                 /  \
     *                                         R   root
     *
     *  Attack the leftmost root-node first.
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // Hit the leftmost root first.
        if(root == null || root.left == null) {
            return root;
        }

        TreeNode tr = upsideDownBinaryTree(root.left);
        upsideDownFormula(root);

        return tr;
    }

    private void upsideDownFormula(TreeNode root) {
        root.left.left = root.right;
        root.left.right = root;

        root.right = null;
        root.left = null;
    }
}
