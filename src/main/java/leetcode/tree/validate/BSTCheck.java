package leetcode.tree.validate;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Created by venkatamunnangi on 12/21/16.
 */
public class BSTCheck {
    public boolean isValidBST(TreeNode root) {
        return validateBST(root, null);
    }

    public boolean validateBST(TreeNode root, TreeNode prev) {
        if(root == null) {
            return true;
        }

        if(!validateBST(root.left, prev)) {
            return false;
        }

        // The condition is l < root < right for BST
        // So, when validating left subtree; we are checking if root <= l, if true then its not bst.
        // When validating right subtree; we are are checking if root.right <= root (since we are swapping using prev.
        if(prev != null && root.val <= prev.val) {
           return false;
        }

        prev = new TreeNode(root.val);

        return validateBST(root.right, prev);
    }

    public static void main(String [] args) {
        BSTCheck bstCheck = new BSTCheck();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(100);

        System.out.println(bstCheck.isValidBST(root));
    }
}
//
//    public boolean isValidBST(TreeNode root) {
//        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    private boolean validate(TreeNode node, long min, long max) {
//        if(node == null) {
//            return true;
//        }
//
//        if(node.val <= min || node.val >= max) {
//            return false;
//        }
//
//        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
//    }