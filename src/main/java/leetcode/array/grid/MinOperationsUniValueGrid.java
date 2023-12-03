package leetcode.array.grid;

import java.util.Arrays;

// https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/
public class MinOperationsUniValueGrid {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int[] nums = new int[m * n];
        int index = 0;

        // Flatten the grid into a one-dimensional array
        for (int[] row : grid) {
            for (int val : row) {
                nums[index++] = val;
            }
        }

        // Sort the array to find the median
        Arrays.sort(nums);

        // Check if all elements are congruent modulo x,
        // since the difference between any 2 numbers need to be a factor of x
        for (int num : nums) {
            if ((num - nums[0]) % x != 0) {
                return -1;
            }
        }

        // Find the median
        int median = nums[nums.length / 2];
        int operations = 0;

        // Count operations needed to make all elements equal to the median
        for (int num : nums) {
            operations += Math.abs(num - median) / x;
        }

        return operations;
    }

    public static void main(String[] args) {
        MinOperationsUniValueGrid solution = new MinOperationsUniValueGrid();
        System.out.println(solution.minOperations(new int[][]{{2, 4}, {6, 8}}, 2)); // Output: 4
        System.out.println(solution.minOperations(new int[][]{{1, 5}, {2, 3}}, 1)); // Output: 5
        System.out.println(solution.minOperations(new int[][]{{1, 2}, {3, 4}}, 2)); // Output: -1
    }
}
