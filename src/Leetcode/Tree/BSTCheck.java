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

        // <= sign for both right and left??
        if(prev != null && root.val <= prev.val) {
           return false;
        }

        prev = new TreeNode(root.val);

        if(!validateBST(root.right,prev))
            return false;

        return true;
    }
}
