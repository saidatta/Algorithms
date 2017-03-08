package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * Created by venkatamunnangi on 3/7/17.
 */
public class FlattenBTToLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }
}
