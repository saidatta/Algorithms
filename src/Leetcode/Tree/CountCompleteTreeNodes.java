package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 *
 * Created by venkatamunnangi on 26/11/16.
 */
public class CountCompleteTreeNodes {
    /**
     * When it is a complete binary tree, then bottom leaf nodes fill up from left to right.
     * So grab the left most node and ur good.
     *
     * @param root
     * @return
     */
    public int heightCompleteTree(TreeNode root) {
        return root == null ? -1 : 1 + heightCompleteTree(root.left);
    }

    public int countNodes(TreeNode root) {
        int h = heightCompleteTree(root);

        if(h >= 0) {
            // If last node is right leaf subtree or left tree subtree.
            int innerHeight = (heightCompleteTree(root.right) == h-1) ? (1 << h)  + countNodes(root.right):
                    (1 << h-1) + countNodes(root.left);
            return innerHeight;
        } else {
            return 0;
        }
    }

    public static void main(String [] args) {
        CountCompleteTreeNodes countCompleteTreeNodes = new CountCompleteTreeNodes();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(11);

        System.out.println(countCompleteTreeNodes.countNodes(root));
    }
}
