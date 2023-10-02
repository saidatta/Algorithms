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
            TreeLinkNode levelLinkedListPointer = null;
            // first node on the next level
            leftmost = null;

            // traverse nodes on the current level
            while (current != null) {
                if (current.left != null) {
                    if (levelLinkedListPointer != null) {
                        levelLinkedListPointer.next = current.left;
                    } else {
                        leftmost = current.left;
                    }
                    levelLinkedListPointer = current.left;
                }

                if (current.right != null) {
                    if (levelLinkedListPointer != null) {
                        levelLinkedListPointer.next = current.right;
                    } else {
                        leftmost = current.right;
                    }
                    levelLinkedListPointer = current.right;
                }

                // move to the next node on the current level
                current = current.next;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        // Create the tree: [1,2,3,4,5,null,7]
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);
        root.right.right = new TreeLinkNode(7);

        PopulatingPointersEachNodeII solution = new PopulatingPointersEachNodeII();
        solution.connect(root);

        // Print the tree level by level
        TreeLinkNode leftmost = root;
        while (leftmost != null) {
            TreeLinkNode current = leftmost;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("#");  // End of the level

            // Move to the next level
            if (leftmost.left != null) {
                leftmost = leftmost.left;
            } else if (leftmost.right != null) {
                leftmost = leftmost.right;
            } else {
                TreeLinkNode tmp = leftmost.next;
                while (tmp != null && tmp.left == null && tmp.right == null) {
                    tmp = tmp.next;
                }
                if (tmp != null) {
                    leftmost = (tmp.left != null) ? tmp.left : tmp.right;
                } else {
                    leftmost = null;
                }
            }
        }
    }
}
