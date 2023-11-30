package leetcode.tree.traversal.bst;

import leetcode.tree.util.TreeNodeParent;

public class SuccessorBST {
    public TreeNodeParent inorderSuccessor(TreeNodeParent node) {
        if (node == null) return null;

        // Case 1: Node has a right child
        if (node.right != null) {
            return findLeftmostNode(node.right);
        }

        // Case 2: Node has no right child
        while (node.parent != null) {
            if (node.parent.left == node) {
                return node.parent;
            }
            node = node.parent;
        }

        // Reached the root without finding in-order successor
        return null;
    }

    private TreeNodeParent findLeftmostNode(TreeNodeParent node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

//    Algorithm:
//    If the node has a right child, and its successor is somewhere lower in the tree. Go to the right once and then
//    as many times to the left as you can. Return the node you end up with.
//
//    Node has no right child, and hence its successor is somewhere upper in the tree. Go up till the node that is left
//    child of its parent. The answer is the parent.
    public TreeNodeParent inorderSuccessorIterative(TreeNodeParent current) {
        // If the node has a right child, go to the right subtree and find the leftmost node
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        // If the node has no right child, find the first ancestor for which the node is in its left subtree
        while (current.parent != null && current == current.parent.right) {
            current = current.parent;
        }
        return current.parent; // This could be null if the node is the last node in in-order traversal
    }

    public static void main(String[] args) {
        TreeNodeParent root = null;

        // Constructing the BST
        int[] values = {5, 3, 6, 2, 4, 1};
        for (int value : values) {
            root = insert(root, value, null);
        }

        // Finding the in-order successor of a specific node
        // For this example, let's find the successor of the node with value 3
        TreeNodeParent node = root.left; // Node with value 3
        SuccessorBST solver = new SuccessorBST();
        TreeNodeParent successor = solver.inorderSuccessor(node);

        if (successor != null) {
            System.out.println("In-order successor of " + node.val + " is: " + successor.val);
        } else {
            System.out.println("In-order successor of " + node.val + " does not exist.");
        }
    }
    // Helper method to add a new node in the BST
    private static TreeNodeParent insert(TreeNodeParent root, int val, TreeNodeParent parent) {
        if (root == null) {
            TreeNodeParent newNode = new TreeNodeParent(val);
            newNode.parent = parent;
            return newNode;
        }

        if (val < root.val) {
            root.left = insert(root.left, val, root);
        } else if (val > root.val) {
            root.right = insert(root.right, val, root);
        }

        return root;
    }

}

