package leetcode.tree.traversal;

import java.util.HashSet;
import java.util.Set;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
public class LowestCommonAncestorBTIV {
    // Class level variable to store the result of Lowest Common Ancestor (LCA)
    TreeNode lca = null;

    /**
     * Function to find the lowest common ancestor of given nodes.
     * @param root: The root of the binary tree.
     * @param nodes: Array of tree nodes for which we need to find LCA.
     * @return TreeNode representing the LCA of the given nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        // Convert the array of tree nodes to a set for efficient lookup
        Set<Integer> targetNodes = new HashSet<>();
        for (TreeNode node : nodes) {
            targetNodes.add(node.val);
        }

        // Call the recursive helper function to find the LCA
        findLCA(root, targetNodes);
        return lca;
    }

    /**
     * Recursive helper function to find the LCA.
     * @param root: The current node.
     * @param nodes: Set of target nodes' values.
     * @return The count of target nodes found in the current subtree.
     */
    private int findLCA(TreeNode root, Set<Integer> nodes) {
        if (root == null) return 0;

        // Check in the left subtree
        int leftCount = findLCA(root.left, nodes);
        // Check in the right subtree
        int rightCount = findLCA(root.right, nodes);

        // Calculate the total count of target nodes found so far
        int foundCount = leftCount + rightCount;
        if (nodes.contains(root.val)) {
            foundCount++;
        }

        // If we have found all the target nodes and haven't set the LCA yet, set it
        if (foundCount == nodes.size() && lca == null) {
            lca = root;
        }

        return foundCount;
    }
}

