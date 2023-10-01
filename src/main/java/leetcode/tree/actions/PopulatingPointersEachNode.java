package leetcode.tree.actions;

import leetcode.tree.util.TreeLinkNode;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/?tab=Description
 *
 * Created by venkatamunnangi on 3/8/17.
 */
public class PopulatingPointersEachNode {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        connectHelper(root.left, root.right);
    }

    private void connectHelper(TreeLinkNode node1, TreeLinkNode node2) {
        if (node1 == null) {
            return;
        }
        node1.next = node2;
        connectHelper(node1.left, node1.right);
        connectHelper(node2.left, node2.right);
        connectHelper(node1.right, node2.left);
    }

    public TreeLinkNode connectIterative(TreeLinkNode root) {
        if (root == null) {
            return null;
        }

        // Start with the root node. There are no next pointers that need to be set up on the first level
        TreeLinkNode leftmost = root;

        // Once we reach the last level, we are done
        while (leftmost.left != null) {
            // Iterate the "linked list" starting from head node and using the next pointers, establish the
            // corresponding links for the next level
            TreeLinkNode head = leftmost;
            while (head != null) {
                // Connection 1
                head.left.next = head.right;

                // Connection 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // Progress along the list (nodes on the current level)
                head = head.next;
            }

            // Move onto the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
