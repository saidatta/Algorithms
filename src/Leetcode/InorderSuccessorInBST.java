package Leetcode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class InorderSuccessorInBST {
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
}
