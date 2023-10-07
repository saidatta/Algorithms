package leetcode.tree.list;

import java.util.ArrayDeque;
import java.util.Deque;
import leetcode.tree.util.TreeNode;

public class ConstructBSTPreorderTraversal {
    // Class level variables to track current index in the preorder traversal,
    // the preorder array itself, and its length.
    private int idx = 0;
    private int[] preorder;
    private int n;

    /**
     * Recursively constructs a BST from the preorder traversal.
     *
     * @param upper The upper limit for the current node's value.
     * @return The root of the constructed subtree.
     */
    private TreeNode constructTree(int upper) {
        // If all elements from the preorder are used, then the subtree is constructed.
        if (idx == n) {
            return null;
        }

        int currentValue = preorder[idx];

        // If the current element's value exceeds the upper limit, it can't be placed here.
        if (currentValue > upper) {
            return null;
        }

        // Move to the next element in preorder and create the current node.
        idx++;
        TreeNode rootNode = new TreeNode(currentValue);

        // Recursively construct the left and right subtrees.
        rootNode.left = constructTree(currentValue);
        rootNode.right = constructTree(upper);

        return rootNode;
    }

    /**
     * Constructs a BST from a given preorder traversal.
     *
     * @param preorder The preorder traversal of the BST.
     * @return The root of the constructed BST.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorder = preorder;
        this.n = preorder.length;

        // Start constructing the tree with the maximum possible value as the upper limit.
        return constructTree(Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        // Test cases
        int[] preorder1 = {8,5,1,7,10,12};
        int[] preorder2 = {1,3};

        ConstructBSTPreorderTraversal solution = new ConstructBSTPreorderTraversal();

        // Construct BSTs from preorder traversals and print in-order representation of the trees
        TreeNode root1 = solution.bstFromPreorder(preorder1);
        System.out.println("In-order traversal of the first tree:");
        printInOrder(root1);
        System.out.println();

        TreeNode root2 = solution.bstFromPreorder(preorder2);
        System.out.println("In-order traversal of the second tree:");
        printInOrder(root2);
        System.out.println();
    }

    // Helper method to print in-order traversal of a tree
    public static void printInOrder(TreeNode root) {
        if (root == null) return;

        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // alternate
    public class Solution {
        private int index = 0;

        public TreeNode bstFromPreorder(int[] preorder) {
            return bstFromPreorderHelper(preorder, Integer.MAX_VALUE);
        }

        private TreeNode bstFromPreorderHelper(int[] preorder, int bound) {
            if (index == preorder.length || preorder[index] > bound) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[index++]);
            root.left = bstFromPreorderHelper(preorder, root.val);
            root.right = bstFromPreorderHelper(preorder, bound);
            return root;
        }

//        The first element of the preorder list is the root.
//        Then, all elements following the root and until the first greater element will be part of the left subtree
//        (since they are all smaller than the root).
//        All elements after the left subtree until the end will be part of the right subtree.
    }
}
//    This code uses a deque to iteratively build the BST. The intuition is to keep potential parent nodes in the deque
//    and find the correct parent for each new node by comparing their values.
