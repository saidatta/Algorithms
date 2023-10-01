package leetcode.tree.actions;

import leetcode.tree.util.TreeLinkNode;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
public class PopulatingPointersEachNodeII {
    public TreeLinkNode connect(TreeLinkNode root) {
        if (root == null) return null;

        TreeLinkNode leftmost = root;

        while (leftmost != null) {
            TreeLinkNode current = leftmost;
            // previous node on the next level
            TreeLinkNode prev = null;
            // first node on the next level
            leftmost = null;

            // traverse nodes on the current level
            while (current != null) {
                if (current.left != null) {
                    if (prev != null) {
                        prev.next = current.left;
                    } else {
                        leftmost = current.left;
                    }
                    prev = current.left;
                }

                if (current.right != null) {
                    if (prev != null) {
                        prev.next = current.right;
                    } else {
                        leftmost = current.right;
                    }
                    prev = current.right;
                }

                // move to the next node on the current level
                current = current.next;
            }
        }

        return root;
    }
}
