package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/invert-binary-tree/?tab=Description
 *
 * Created by venkatamunnangi on 23/11/16.
 */
public class InvertBinaryTree {

    public TreeNode invertTree2(TreeNode root) {
        if(root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            TreeNode node = q.poll();

            // inversion formula
            // Remember that swapping between subtrees is not valid.
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            // order of offering is important.
            if(node.right != null) {
                q.offer(node.right);
            }

            if(node.left != null) {
                q.offer(node.left);
            }
        }

        return root;
    }
}
