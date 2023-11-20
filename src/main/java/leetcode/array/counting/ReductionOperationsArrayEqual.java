package leetcode.array.counting;

import java.util.Arrays;

// https://leetcode.com/problems/reduction-operations-to-make-the-array-elements-equal/description/
public class ReductionOperationsArrayEqual {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int totalOps = 0, ops = 0, n = nums.length;

        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                ops++; // Increase the operation count when we encounter a new element
            }
            totalOps += ops; // Add the current operation count for this element
        }

        return totalOps;
    }

    public static void main(String[] args) {
        ReductionOperationsArrayEqual solution = new ReductionOperationsArrayEqual();
        System.out.println(solution.reductionOperations(new int[]{5, 1, 3})); // Output: 3
        System.out.println(solution.reductionOperations(new int[]{1, 1, 1})); // Output: 0
        System.out.println(solution.reductionOperations(new int[]{1, 1, 2, 2, 3})); // Output: 4
    }
}
