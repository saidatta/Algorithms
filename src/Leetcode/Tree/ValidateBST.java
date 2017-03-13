package Leetcode.Tree;

import Leetcode.TreeNode;

/**
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
        if(min != null && root.val >= max || (max == null || root.val <= min)) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public static void main(String [] args) {
        TreeNode tr = new TreeNode(20);
        tr.left = new TreeNode(18);
        tr.left.left = new TreeNode(1);
        tr.left.right = new TreeNode(25);

        ValidateBST validateBST = new ValidateBST();
        System.out.println(validateBST.isValidBST(tr));
    }
}
