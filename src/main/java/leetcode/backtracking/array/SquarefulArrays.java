package leetcode.backtracking.array;

import java.util.Arrays;

// https://leetcode.com/problems/number-of-squareful-arrays/
public class SquarefulArrays {
    private int count = 0;

    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums); // Sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new int[nums.length], 0);
        return count;
    }

    private void backtrack(int[] nums, boolean[] used, int[] path, int position) {
        if (position == nums.length) {
            count++; // Found a valid permutation
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Check if the number is already used or if it's the same as the previous number to avoid duplicates
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;

            // If it's the first element or the sum with the previous element is a perfect square
            if (position == 0 || isPerfectSquare(nums[i] + path[position - 1])) {
                used[i] = true;
                path[position] = nums[i];
                backtrack(nums, used, path, position + 1); // Recurse
                used[i] = false; // Backtrack
            }
        }
    }

    private boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static void main(String[] args) {
        SquarefulArrays solution = new SquarefulArrays();
        System.out.println("Number of squareful permutations: " + solution.numSquarefulPerms(new int[]{1, 17, 8})); // Output: 2
        System.out.println("Number of squareful permutations: " + solution.numSquarefulPerms(new int[]{2, 2, 2})); // Output: 1
    }
}

