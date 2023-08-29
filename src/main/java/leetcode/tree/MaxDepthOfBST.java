package leetcode.tree;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/?tab=Description
 *
 * Created by venkatamunnangi on 3/7/17.
 */
public class MaxDepthOfBST {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    public static void main(String [] args) {
        MaxDepthOfBST m = new MaxDepthOfBST();
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.right = new TreeNode(3);

        System.out.println(m.maxDepth(r));
    }
}
