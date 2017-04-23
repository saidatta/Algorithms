package Leetcode.Tree;

import Leetcode.TreeNode;

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

        if(!validateBST(root.left, prev))
            return false;

        // The condition is l < root < right for BST
        // So, when validating left subtree; we are checking if l >= root, if true then its not bst.
        // When validating right subtree; we are are checking if root >= root.right (since we are swapping using prev.
        if(prev != null && root.val <= prev.val) {
           return false;
        }

        prev = new TreeNode(root.val);

        if(!validateBST(root.right,prev))
            return false;

        return true;
    }

    public static void main(String [] args) {
        BSTCheck bstCheck = new BSTCheck();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(100);

        System.out.println(bstCheck.isValidBST(root));
    }
}
