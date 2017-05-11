package Leetcode.Tree;

import Leetcode.TreeNode;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h
 * nodes inclusive at the last level h.
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
            // inner height
            // 2^0 + 2^1 + 2^2 +.....
            return (heightCompleteTree(root.right) == h-1) ? (1 << h)  + countNodes(root.right):
                    (1 << h-1) + countNodes(root.left);
        } else {
            return 0;
        }
    }

    public static void main(String [] args) {
        CountCompleteTreeNodes countCompleteTreeNodes = new CountCompleteTreeNodes();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1);
        root.right = new TreeNode(11);

        out.println(countCompleteTreeNodes.countNodes(root));
    }
}
