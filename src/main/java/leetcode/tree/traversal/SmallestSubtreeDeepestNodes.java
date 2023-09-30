package leetcode.tree.traversal;

import leetcode.tree.util.TreeNode;

/**
 * This class provides a solution to find the subtree that contains all the deepest nodes
 * in a binary tree.
 */
// https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/
public class SmallestSubtreeDeepestNodes {

    /**
     * Returns the subtree that contains all the deepest nodes.
     *
     * @param root Root node of the binary tree.
     * @return The subtree's root node.
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return findDeepestSubtree(root).node;
    }

    /**
     * A recursive helper method to find the subtree with all deepest nodes.
     *
     * @param node The current node being processed.
     * @return A Result object containing the node with the deepest subtree and its depth.
     */
    private Result findDeepestSubtree(TreeNode node) {
        // Base condition: if node is null
        if (node == null) {
            return new Result(null, 0);
        }

        // Recursively find results for left and right subtrees
        Result leftResult = findDeepestSubtree(node.left);
        Result rightResult = findDeepestSubtree(node.right);

        // If left subtree is deeper, return its result incremented by 1
        if (leftResult.dist > rightResult.dist) {
            return new Result(leftResult.node, leftResult.dist + 1);
        }

        // If right subtree is deeper, return its result incremented by 1
        if (leftResult.dist < rightResult.dist) {
            return new Result(rightResult.node, rightResult.dist + 1);
        }

        // If both subtrees have the same depth, return current node as result
        return new Result(node, leftResult.dist + 1);
    }
}

/**
 * Helper class to represent the result containing:
 * 1. node: The node that is equal to or an ancestor of all the deepest nodes of a subtree.
 * 2. dist: The depth (number of nodes) from the root of the subtree to its deepest node.
 */
class Result {
    TreeNode node;
    int dist;

    Result(TreeNode n, int d) {
        this.node = n;
        this.dist = d;
    }
}