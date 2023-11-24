package leetcode.tree.sorting;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/maximum-binary-tree/description/
public class MaxBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // Call the recursive construct function with the whole array
        return construct(nums, 0, nums.length);
    }

    public TreeNode construct(int[] nums, int left, int right) {
        // Base case: if the subarray is empty, return null
        if (left == right)
            return null;

        // Find the index of the maximum value in the current subarray
        int maxIndex = findMaxIndex(nums, left, right);

        // Create a new tree node with the maximum value
        TreeNode rootNode = new TreeNode(nums[maxIndex]);

        // Recursively construct the left subtree from the subarray before the max value
        rootNode.left = construct(nums, left, maxIndex);

        // Recursively construct the right subtree from the subarray after the max value
        rootNode.right = construct(nums, maxIndex + 1, right);

        // Return the constructed tree
        return rootNode;
    }

    public int findMaxIndex(int[] nums, int left, int right) {
        // Initialize maxIndex to the left bound of the subarray
        int maxIndex = left;

        // Iterate through the subarray to find the index of the maximum value
        for (int i = left; i < right; i++) {
            if (nums[maxIndex] < nums[i])
                maxIndex = i;
        }

        // Return the index of the maximum value
        return maxIndex;
    }
}


