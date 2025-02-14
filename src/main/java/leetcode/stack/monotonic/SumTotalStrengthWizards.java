package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/sum-of-total-strength-of-wizards/description/
//In the problem "Sum of Total Strength of Wizards," you are given an array where each element represents the strength
// of a wizard. The task is to calculate the sum of the total strengths of all possible contiguous subarrays of this
// array. Each subarray's total strength is determined by multiplying the smallest strength in the subarray by the sum
// of all strengths within that subarray. For example, with an input array of [1,3,1,2], one of the subarrays is [1,3]
// which has a total strength of \(1 \times (1+3) = 4\), and the goal is to sum such calculations for all subarrays and
// return the result modulo \(10^9 + 7\) to handle large numbers.
// https://www.youtube.com/watch?v=HYCMvFxWO7w
public class SumTotalStrengthWizards {

    public static void main(String[] args) {
        var thread = new Thread(() -> {
            System.out.println("Hello world from a Java thread");
        });
        thread.start();
    }

    /**
     * Calculates the sum of the total strengths of all contiguous subarrays of wizards' strengths.
     * Each subarray's total strength is defined as the product of the subarray's minimum strength
     * and the sum of all strengths within that subarray.
     *
     * The method uses a combination of techniques:
     * 1. Monotonic stacks to determine the nearest smaller values to the left and right for each element.
     *    This helps in identifying the range over which each element is the minimum.
     * 2. Prefix sums to efficiently calculate the sum of elements in any subarray.
     * 3. Modular arithmetic to handle large numbers, as the results and intermediate calculations
     *    could exceed standard data type limits.
     *
     * Steps:
     * - Use two monotonic stacks to find indices of the closest smaller elements on both sides for each element.
     * - Compute prefix sums and prefix sums of prefix sums to quickly calculate subarray sums.
     * - For each element in the array, compute its contribution to the total strength considering
     *   it as the minimum value in all subarrays where it can be the minimum.
     * - Sum these contributions and return the total, modulo 10^9 + 7.
     *
     * @param strength an array of integers where strength[i] denotes the strength of the i-th wizard.
     * @return the sum of the total strengths of all subarrays modulo 10^9 + 7.
     */
    public int totalStrength(int[] strength) {
        final int MOD = 1_000_000_007; // Modular value as specified in the problem
        int n = strength.length; // Number of wizards

        // Arrays to record the closest smaller indices on both sides
        int[] leftIndex = new int[n]; // For each position, index of the previous smaller element
        int[] rightIndex = new int[n]; // For each position, index of the next smaller element
        Arrays.fill(rightIndex, n);
        Arrays.fill(leftIndex, -1);

        // Stack for finding the closest smaller elements using a monotonic approach
        Stack<Integer> stack = new Stack<>();

        // Find rightIndex for each element
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                rightIndex[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear(); // Clear the stack to reuse for left indices

        // Find leftIndex for each element
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) {
                leftIndex[stack.pop()] = i;
            }
            stack.push(i);
        }

        // Prepare prefix sum array to handle sum of strengths calculations efficiently
        long answer = 0;
        long[] prefixSumOfPrefixSum = new long[n + 2]; // Extra space to handle bounds gracefully
        for (int i = 0; i < n; ++i)
            prefixSumOfPrefixSum[i + 2] = (prefixSumOfPrefixSum[i + 1] + strength[i]) % MOD;
        for (int i = 1; i <= n; ++i)
            prefixSumOfPrefixSum[i + 1] = (prefixSumOfPrefixSum[i + 1] + prefixSumOfPrefixSum[i]) % MOD;

        // Calculate the total strength of all subarrays where each element is the minimum once
        for (int i = 0; i < n; ++i) {
            int leftBound = leftIndex[i], rightBound = rightIndex[i];
            int leftCount = i - leftBound, rightCount = rightBound - i;

            // Positive and negative prefix sums for the subarray calculations
            long negPrefixSum = (prefixSumOfPrefixSum[i + 1] - prefixSumOfPrefixSum[i - leftCount + 1] + MOD) % MOD;
            long posPrefixSum = (prefixSumOfPrefixSum[i + rightCount + 1] - prefixSumOfPrefixSum[i + 1] + MOD) % MOD;

            // Calculate the strength contribution for current wizard being the minimum in its subarrays
            answer = (answer + (posPrefixSum * leftCount % MOD - negPrefixSum * rightCount % MOD + MOD) % MOD * strength[i] % MOD) % MOD;
        }

        return (int) answer;
    }
}
