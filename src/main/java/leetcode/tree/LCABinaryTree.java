package leetcode.tree;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/?tab=Description
 *
 * DUPLICATE VALUE NOT ALLOWED
 *
 * Created by venkatamunnangi on 3/7/17.
 */
public class LCABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }

        /// one value is null in here.
        return (left != null) ? left : right;
    }

    public static void main(String [] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        LCABinaryTree lcaBinaryTree = new LCABinaryTree();
        System.out.println(lcaBinaryTree.lowestCommonAncestor(root, new TreeNode(3), new TreeNode(4)).val);
    }
}
