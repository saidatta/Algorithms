package Leetcode.DP;

import Leetcode.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 3/3/17.
 */

/**
 * In this case, the subproblems are defined as "all the BSTs that can be created from a sequence of consecutive
 * numbers". For example, one subproblem could be "all the BSTs that can be created from the numbers 1 to 3".
 *
 * The DP matrix dp[][] is used to store the solutions to these subproblems. The entry dp[i][j] stores all the BSTs
 * that can be generated from the sequence of numbers i to j.
 *
 * The function genTreeHelper(start, end, dp) generates all the BSTs from the sequence of numbers start to end, and
 * stores the result in dp[start][end]. This function works recursively:
 *
 * If start > end, it means that there are no numbers in the sequence. In this case, there is only one "tree" that can
 * be generated, which is an empty tree. So, it adds null to the list of trees and returns it.
 *
 * If dp[start][end] is not null, it means that this subproblem has been solved before. So, it can return the solution
 * directly from dp[start][end] without re-computing it.
 *
 * If dp[start][end] is null, it means that this subproblem has not been solved before. So, it initializes
 * dp[start][end] to an empty list of trees.
 *
 * Then, it iterates over all the numbers i in the sequence start to end. For each i, it considers i as the root of the
 * BST. All the numbers less than i will be in the left subtree, and all the numbers greater than i will be in the right
 * subtree. So, it recursively generates all the BSTs for the left and right subtrees, and for each pair of left and
 * right trees, it creates a new tree with i as the root and adds it to dp[start][end].
 *
 * Finally, it returns dp[start][end], which is the list of all the BSTs that can be generated from the sequence of
 * numbers start to end.
 *
 * So, the DP solution works by recursively breaking down the problem into smaller subproblems, solving each subproblem
 * just once, and reusing the solutions to previous subproblems to build up the solutions to larger problems. This is
 * how the DP solution logically solves the problem.
 */

public class UniqueBST2 {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] dp = new ArrayList[n + 1][n + 1];
        return genTreeHelper(1, n, dp);
    }

    List<TreeNode> genTreeHelper(int start, int end, List<TreeNode>[][] dp) {
        if (start > end) {
            List<TreeNode> leaf = new ArrayList<>();
            leaf.add(null);
            return leaf;
        }
        if (dp[start][end] != null) {
            return dp[start][end];
        }
        dp[start][end] = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = genTreeHelper(start, i - 1, dp);
            List<TreeNode> right = genTreeHelper(i + 1, end, dp);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    dp[start][end].add(new TreeNode(i, l, r));
                }
            }
        }
        return dp[start][end];
    }
}
