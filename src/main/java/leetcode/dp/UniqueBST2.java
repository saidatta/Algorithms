package leetcode.dp;

import leetcode.tree.util.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a solution to generate all unique Binary Search Trees (BSTs)
 * that can be constructed given a number 'n'. The uniqueness is based on the
 * structure of the BST. Each BST has values from 1 to n.
 *
 * A dynamic programming approach is used to solve this problem, where each subproblem
 * represents the BSTs that can be constructed for a given range of numbers.
 */
public class UniqueBST2 {
    /**
     * Generate all unique BSTs with values from 1 to 'n'.
     *
     * @param n The range of values in BST (1 to n).
     * @return List of unique BSTs.
     */
    public List<TreeNode> generateTrees(int n) {
        // Initialize the DP table with null values.
        List<TreeNode>[][] dp = new ArrayList[n + 1][n + 1];
        return generateTreesHelper(1, n, dp);
    }

    /**
     * Helper function to generate BSTs for a given range using a DP table.
     *
     * @param start Starting value of the range.
     * @param end Ending value of the range.
     * @param dp DP table containing previously computed solutions.
     * @return List of BSTs for the given range.
     */
    private List<TreeNode> generateTreesHelper(int start, int end, List<TreeNode>[][] dp) {
        // Base condition: If range is invalid (start > end), return list containing only null.
        if (start > end) {
            List<TreeNode> list = new ArrayList<>();
            list.add(null);
            return list;
        }

        // If this subproblem is already solved, return the answer directly from DP table.
        if (dp[start][end] != null) {
            return dp[start][end];
        }

        // Initialize the list for this subproblem.
        dp[start][end] = new ArrayList<>();

        // Iterate through all numbers in the range. Each number can be the root.
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTreesHelper(start, i - 1, dp); // Generate left sub-trees
            List<TreeNode> rightTrees = generateTreesHelper(i + 1, end, dp); // Generate right sub-trees

            // Combine left and right sub-trees to form new trees with 'i' as root.
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    dp[start][end].add(new TreeNode(i, left, right));
                }
            }
        }

        return dp[start][end];
    }

    public static void main(String[] args) {
        UniqueBST2 solver = new UniqueBST2();

        int n = 3;
        List<TreeNode> trees = solver.generateTrees(n);

        System.out.println("Unique BSTs with " + n + " nodes:");
        for (TreeNode root : trees) {
            printTree(root);
            System.out.println("-----");
        }
    }

    // Utility function to print a tree in inorder traversal.
    private static void printTree(TreeNode root) {
        if (root == null) return;
        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }
}