package leetcode.tree.traversal;

import Int.fb.NaryTreeNode;

// https://leetcode.com/problems/diameter-of-n-ary-tree/
class DiameterNaryTree {
    // Global variable to keep track of the maximum diameter
    private int maxDiameter = 0;

    public int diameter(NaryTreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(NaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        // Initialize first and second max depths
        int max1 = 0, max2 = 0;

        // Explore all children of the current node
        for (NaryTreeNode child : node.children) {
            int depth = maxDepth(child);

            // Update the first and second max depths
            if (depth > max1) {
                max2 = max1;
                max1 = depth;
            } else if (depth > max2) {
                max2 = depth;
            }
        }

        // Update the global maximum diameter
        maxDiameter = Math.max(maxDiameter, max1 + max2);

        // Return the depth of the current node
        return max1 + 1;
    }
}
