package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/delete-node-in-a-bst/
public class DeleteNodeBST {
//    If key > root.val then delete the node to delete is in the right subtree
//    root.right = deleteNode(root.right, key).
//
//    If key < root.val then delete the node to delete is in the left subtree
//    root.left = deleteNode(root.left, key).
//
//    If key == root.val then the node to delete is right here. Let's do it :
//
//    If the node is a leaf, the delete process is straightforward :
//    root = null.
//
//    If the node is not a leaf and has the right child, then replace the node
//    value by a successor value root.val = successor.val,
//    and then recursively delete
//    the successor in the right subtree root.right = deleteNode(root.right, root.val).
//
//    If the node is not a leaf and has only the left child, then replace the node
//    value by a predecessor value root.val = predecessor.val, and then recursively delete
//    the predecessor in the left subtree root.left = deleteNode(root.left, root.val).
//
//    Return root.
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // Delete from the right subtree
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // Delete from the left subtree
            root.left = deleteNode(root.left, key);
        } else {
            // the node is a leaf
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                // the node is not a leaf and has a right child
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                // the node is not a leaf, has no right child, and has a left child
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }

        return root;
    }

    /*
   One step right and then always left
   */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    /*
    One step left and then always right
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }
}

//Overview: Three Facts to Know about BST

//
//Inorder traversal of BST is an array sorted in the ascending order.
//
//To compute inorder traversal follow the direction Left -> Node -> Right.

// Successor = "after node", i.e. the next node, or the smallest node after the current one.
//
//        It's also the next node in the inorder traversal. To find a successor, go to the right once
//        and then as many times to the left as you could.
//
//
//        Predecessor = "before node", i.e. the previous node, or the largest node before the current one.
//
//        It's also the previous node in the inorder traversal. To find a predecessor, go to the left once
//        and then as many times to the right as you could.
//
//
//        bla
//
//
//
//        Approach 1: Recursion
//        Intuition
//        There are three possible situations here :
//
//        Node is a leaf, and one could delete it straightforward : node = null.
//        bla
//
//        Node is not a leaf and has a right child. Then the node could be replaced by its
//        successor which is somewhere lower in the right subtree. Then one could proceed down recursively to
//        delete the successor.
//        bla
//
//        Node is not a leaf, has no right child and has a left child. That means that its successor is somewhere
//        upper in the tree but we don't want to go back.
//        Let's use the predecessor here which is somewhere lower in the left subtree.
//        The node could be replaced by its
//        predecessor and then one could proceed down recursively to delete the predecessor.
//        bla
//
//        Algorithm
//        If key > root.val then delete the node to delete is in the right subtree
//        root.right = deleteNode(root.right, key).
//
//        If key < root.val then delete the node to delete is in the left subtree
//        root.left = deleteNode(root.left, key).
//
//        If key == root.val then the node to delete is right here. Let's do it :
//
//        If the node is a leaf, the delete process is straightforward :
//        root = null.
//
//        If the node is not a leaf and has the right child, then replace the node
//        value by a successor value root.val = successor.val,
//        and then recursively delete
//        the successor in the right subtree root.right = deleteNode(root.right, root.val).
//
//        If the node is not a leaf and has only the left child, then replace the node
//        value by a predecessor value root.val = predecessor.val, and then recursively delete
//        the predecessor in the left subtree root.left = deleteNode(root.left, root.val).
//
//        Return root.
//
//        Implementation
//        bla
//
//        Note that in our implementation, successor/predecessor has a different function signature from what we
//        define above because we only need the value of the returned node when replacing the current node with its
//        successor or predecessor. But we still provide a full definition and algorithm to locate those nodes in
//        case of encountering a similar problem and need to get the TreeNode object instead of just a value.