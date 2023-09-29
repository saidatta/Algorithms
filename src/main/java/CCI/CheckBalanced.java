package CCI;

import leetcode.tree.util.TreeNode;

/**
 * Created by venkatamunnangi on 12/16/16.
 */
public class CheckBalanced {
    boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        int leftHeight = getHeight(root.left);
        int rightheight = getHeight(root.right);

        int diff = leftHeight - rightheight;

        if(diff > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    int getHeight(TreeNode r) {
        if(r == null) return  -1;

        return Math.max(getHeight(r.left), getHeight(r.right)) + 1;
    }


    int getHeight2(TreeNode r) {
        if(r == null) return  -1;




        return Math.max(getHeight(r.left), getHeight(r.right)) + 1;
    }
}
