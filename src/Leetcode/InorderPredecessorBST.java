package Leetcode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class InorderPredecessorBST {
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }


        if(root.val >= p.val) {
            return inorderPredecessor(root.right, p);
        } else {
            TreeNode left = inorderPredecessor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}
