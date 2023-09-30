package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

/**
 * This class provides a solution to generate all possible full binary trees
 * (FBT) with a given number of nodes. A full binary tree is a binary tree where
 * each node has exactly 0 or 2 children.
 *
 * https://leetcode.com/problems/all-possible-full-binary-trees/description/
 */
public class AllPossibleFullBinaryTrees {

    /**
     * Generate all full binary trees with 'n' nodes.
     *
     * @param n Number of nodes in the tree.
     * @return List of possible full binary trees.
     */
    public List<TreeNode> allPossibleFBT(int n) {
        // Return empty list if n is even, as FBT can only have an odd number of nodes
        if (n % 2 == 0) {
            return new ArrayList<>();
        }

        // Create a DP table to store intermediate results
        List<List<TreeNode>> dp = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }

        // Base case: An FBT with 1 node
        dp.get(1).add(new TreeNode(0));

        // Loop through and construct FBTs using smaller pre-constructed trees
        for (int count = 3; count <= n; count += 2) {
            for (int leftCount = 1; leftCount < count - 1; leftCount += 2) {
                int rightCount = count - 1 - leftCount;

                // Combine trees from left and right counts to form new trees
                for (TreeNode leftTree : dp.get(leftCount)) {
                    for (TreeNode rightTree : dp.get(rightCount)) {
                        TreeNode root = new TreeNode(0, leftTree, rightTree);
                        dp.get(count).add(root);
                    }
                }
            }
        }

        return dp.get(n);
    }
}