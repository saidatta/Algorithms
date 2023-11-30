package leetcode.tree.traversal.bst;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/closest-binary-search-tree-value/description/
public class ClosestBSTValue {

    /**
     * Finds the value in the BST that is closest to the target.
     * If there are multiple answers, returns the smallest.
     *
     * @param root   The root node of the BST.
     * @param target The target value.
     * @return The value in the BST that is closest to the target.
     */
    public int closestValue(TreeNode root, double target) {
        int closestValue = root.val; // Start with the root value as the initial closest value

        while (root != null) {
            int currentValue = root.val;

            // Check if the current value is closer to target than the current closest value
            if (isCloser(currentValue, closestValue, target)) {
                closestValue = currentValue;
            }

            // Move to the next relevant subtree based on target
            root = target < currentValue ? root.left : root.right;
        }

        return closestValue;
    }

    /**
     * Determines if value1 is closer to the target than value2.
     *
     * @param value1 The first value.
     * @param value2 The second value.
     * @param target The target value.
     * @return True if value1 is closer or equally close but smaller than value2, false otherwise.
     */
    private boolean isCloser(int value1, int value2, double target) {
        double diff1 = Math.abs(value1 - target);
        double diff2 = Math.abs(value2 - target);

        return diff1 < diff2 || (diff1 == diff2 && value1 < value2);
    }
}