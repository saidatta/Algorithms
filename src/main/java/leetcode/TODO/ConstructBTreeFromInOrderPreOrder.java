package leetcode.TODO;

import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/#/description
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 */
public class ConstructBTreeFromInOrderPreOrder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // Root node is the first node in preorder
        TreeNode root = new TreeNode(preorder[preStart]);

        // Find the index of the root in inorder
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }

        // left tree node count
        int leftTreeCount = inIndex - inStart;

        // Build left and right subtrees
        root.left = buildTree(preorder, preStart + 1, preStart + leftTreeCount, inorder, inStart, inIndex - 1);
        root.right = buildTree(preorder, preStart + leftTreeCount + 1, preEnd, inorder, inIndex + 1, inEnd);

        return root;
    }
}
