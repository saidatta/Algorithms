package Leetcode.Tree;

import Leetcode.TreeNode;

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
public class DiamaterOfBT {

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int rootDiameter = getHeight(root.left) + getHeight(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
    }

    public static int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }


    public static void main(String [] args) {
        DiamaterOfBT diamaterOfBT = new DiamaterOfBT();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);


        System.out.println(diamaterOfBT.diameterOfBinaryTree(root));
    }
}
