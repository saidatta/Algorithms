package leetcode.tree.list;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/editorial/
public class FlattenBTLinkedList {
    /**
     * Flattens a binary tree to a linked list in-place.
     *
     * @param root Root of the binary tree.
     */
    public void flatten(TreeNode root) {
        // Null check
        if (root == null) {
            return;
        }

        TreeNode currentNode = root;

        while (currentNode != null) {
            // If the node has a left child, we need to rearrange
            if (currentNode.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode rightmostOfLeft = getRightmost(currentNode.left);

                // Rearrange pointers
                rightmostOfLeft.right = currentNode.right;
                currentNode.right = currentNode.left;
                currentNode.left = null;
            }

            // Move to the next node
            currentNode = currentNode.right;
        }
    }

    /**
     * Returns the rightmost node of the given subtree.
     *
     * @param node Root of the subtree.
     * @return Rightmost node of the subtree.
     */
    private TreeNode getRightmost(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // Time = O(N) because we traverse the whole binary tree
// space = O(N) because for a only left child binary tree. The call stack is O(N)
    class Solution {
        private TreeNode head;
        public void flatten(TreeNode root) {
            if (root == null) return;
            // postorder
            flatten(root.right);
            flatten(root.left);
            root.right = head;
            root.left = null;
            head = root;
        }
    }
}
