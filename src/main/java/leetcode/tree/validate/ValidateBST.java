package leetcode.tree.validate;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/#/description
 *
 * Created by venkatamunnangi on 3/13/17.
 */
public class ValidateBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        // root nodes > left node && root < right
        boolean bstProperty = (min == null || root.val > min) && (max == null || root.val < max);

        return bstProperty && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }


    public boolean isValidBST2(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        // An empty tree is a valid BST
        if (node == null) {
            return true;
        }

        // The current node's value must be between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // The left subtree of the node must be a BST and all its values must be less than node.val
        // The right subtree of the node must be a BST and all its values must be greater than node.val
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    public static void main(String [] args) {
        // invalid bst below.
        TreeNode tr = new TreeNode(20);
        tr.left = new TreeNode(18);
        tr.left.left = new TreeNode(1);
        tr.left.right = new TreeNode(25);

        ValidateBST validateBST = new ValidateBST();
        System.out.println(validateBST.isValidBST(tr));
    }
}
