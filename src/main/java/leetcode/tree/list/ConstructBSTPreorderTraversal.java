package leetcode.tree.list;

import leetcode.tree.util.TreeNode;

public class ConstructBSTPreorderTraversal {

    // Current index being processed in the preorder array
    private int currentIndex = 0;

    // The preorder traversal values
    private int[] preorderValues;

    // Total number of nodes in the tree
    private int totalNodes;

    /**
     * Recursive helper function to construct the BST from preorder traversal.
     *
     * @param upperBound The upper bound value that the current node value shouldn't exceed.
     * @return The constructed TreeNode.
     */
    private TreeNode constructTree(int upperBound) {
        // Check if all elements from preorder are used
        if (currentIndex == totalNodes) return null;

        int nodeValue = preorderValues[currentIndex];

        // Check if the current element couldn't be placed here to meet BST requirements
        if (nodeValue > upperBound) return null;

        // Move to the next value in the preorder traversal
        currentIndex++;

        // Create a TreeNode with the current value
        TreeNode currentNode = new TreeNode(nodeValue);

        // Recursively construct left and right subtrees
        currentNode.left = constructTree(nodeValue);
        currentNode.right = constructTree(upperBound);

        return currentNode;
    }

    /**
     * Entry function to construct the BST from its preorder traversal.
     *
     * @param preorder The given preorder traversal.
     * @return The root of the constructed BST.
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        this.preorderValues = preorder;
        this.totalNodes = preorder.length;
        return constructTree(Integer.MAX_VALUE);
    }
}
