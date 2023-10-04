package leetcode.tree.actions;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/invert-binary-tree/?tab=Description
 *
 * Created by venkatamunnangi on 23/11/16.
 */
public class InvertBinaryTree {

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            // inversion formula
            // Remember that swapping between subtrees is not valid.
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            // order of offering is important.
            if (node.right != null) {
                q.offer(node.right);
            }

            if (node.left != null) {
                q.offer(node.left);
            }
        }

        return root;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // Swap left and right children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // Recursively invert the left and right subtrees
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        // Test the invertTree function
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );
        TreeNode invertedRoot = invertTree(root);
        // You can now print or process the inverted tree as needed
    }
}
