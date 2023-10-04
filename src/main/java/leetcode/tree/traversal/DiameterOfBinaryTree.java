package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/#/description
 *
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * o(n^2) - since it has to travel for height as well.
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class DiameterOfBinaryTree {
    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0; // reset the diameter
        depth(root);
        return diameter;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // Update the diameter using the depths of the left and right subtrees
        diameter = Math.max(diameter, leftDepth + rightDepth);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(solution.diameterOfBinaryTree(root));  // Expected output: 3
    }
}
