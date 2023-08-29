package leetcode.tree;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class InorderPredecessorBST {
    // either it is left node or root node.
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }

        if(root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }

    // either it is right node or root node.
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }

        if(root.val >= p.val) {
            return inorderPredecessor(root.left, p);
        } else {
            TreeNode right = inorderPredecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }
}
