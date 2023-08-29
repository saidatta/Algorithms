package leetcode.array;

// https://leetcode.com/problems/max-pair-sum-in-an-array/
public class MaxPairSumArray {

    // Constants to help make the code self-documenting
    private static final int MAX_SIZE = 10001;

    /**
     * Computes the maximum sum of any two numbers such that they share the same highest digit.
     *
     * @param nums Array of integers.
     * @return The maximum sum or -1 if none found.
     */
    public int maxSum(int[] nums) {
        int maxSum = -1;
        int[] highestDigits = new int[MAX_SIZE];

        // Store the highest digit of each number
        for (int n : nums) {
            highestDigits[n] = highestDigit(n);
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (highestDigits[nums[i]] == highestDigits[nums[j]]) {
                    maxSum = Math.max(maxSum, nums[i] + nums[j]);
                }
            }
        }

        return maxSum;
    }

    /**
     * Returns the highest digit in a number.
     *
     * @param num The number to check.
     * @return The highest digit.
     */
    private int highestDigit(int num) {
        int highest = 0;
        while (num > 0) {
            highest = Math.max(highest, num % 10);
            num /= 10;
        }
        return highest;
    }

    public static void main(String[] args) {
        MaxPairSumArray sol = new MaxPairSumArray();
        System.out.println(sol.maxSum(new int[]{51, 71, 17, 24, 42})); // 88
        System.out.println(sol.maxSum(new int[]{1, 2, 3, 4})); // -1
    }
}
