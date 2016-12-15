package Leetcode;

/**
 * Created by venkatamunnangi on 26/11/16.
 */
public class CountCompleteTreeNodes {
    /**
     * When it is a complete binary tree, then bottom leaf nodes fill up from left to right.
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
            return (heightCompleteTree(root.right) == h-1) ? (1 << h) + countNodes(root.right):
                                                             (1 << h-1) + countNodes(root.left);
        } else {
            return 0;
        }
    }
}
