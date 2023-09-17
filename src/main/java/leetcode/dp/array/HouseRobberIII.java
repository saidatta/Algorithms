package leetcode.dp.array;

import leetcode.TreeNode;

/**
 * https://leetcode.com/problems/house-robber-iii
 *
 * Created by venkatamunnangi on 9/21/19.
 */
public class HouseRobberIII {
    private static final int EXCLUDED = 0;
    private static final int INCLUDED = 1;

    /**
     * Determines the maximum amount you can rob from tree houses without robbing two adjacent houses.
     * @param root Root of the tree.
     * @return The maximum amount of
    money you can rob.
     */
    public int rob(TreeNode root) {
        int[] values = calculateMaxRobbery(root);
        return Math.max(values[EXCLUDED], values[INCLUDED]);
    }

    /**
     * Helper function to compute the maximum value that can be obtained from the current subtree
     * by either including or excluding the current node.
     * @param node Current tree node.
     * @return An array where the first element represents the maximum value when the current node is excluded,
     * and the second element represents the value when the node is included.
     */
    private int[] calculateMaxRobbery(TreeNode node) {
        int[] result = new int[2];

        if (node == null) {
            return result;
        }

        int[] leftValues = calculateMaxRobbery(node.left);
        int[] rightValues = calculateMaxRobbery(node.right);

        // When the current node is excluded,
        // we can choose to either include or exclude its child nodes to maximize the value.
        result[EXCLUDED] = Math.max(leftValues[EXCLUDED], leftValues[INCLUDED]) +
                Math.max(rightValues[EXCLUDED], rightValues[INCLUDED]);

        // When the current node is included, we must exclude its child nodes to avoid adjacent robbing.
        result[INCLUDED] = leftValues[EXCLUDED] + rightValues[EXCLUDED] + node.val;

        return result;
    }
}
