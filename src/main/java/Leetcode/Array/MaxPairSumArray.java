package Leetcode.Array;

// https://leetcode.com/problems/max-pair-sum-in-an-array/
public class MaxPairSumArray {
    public int maxSum(int[] nums) {
        int[] maxValues = new int[10];
        int[] secondMaxValues = new int[10];

        // Step 3: Iterate over the numbers in the list
        for (int num : nums) {
            int maxDigit = getMaxDigit(num);

            if (num > maxValues[maxDigit]) {
                secondMaxValues[maxDigit] = maxValues[maxDigit];
                maxValues[maxDigit] = num;
            } else if (num > secondMaxValues[maxDigit]) {
                secondMaxValues[maxDigit] = num;
            }
        }

        // Step 4: Calculate the result
        int result = -1;
        for (int i = 0; i < 10; i++) {
            if (secondMaxValues[i] > 0) {
                result = Math.max(result, maxValues[i] + secondMaxValues[i]);
            }
        }

        return result;
    }

    private int getMaxDigit(int num) {
        int maxDigit = 0;
        while (num > 0) {
            maxDigit = Math.max(maxDigit, num % 10);
            num /= 10;
        }
        return maxDigit;
    }

    public static void main(String[] args) {
        MaxPairSumArray sol = new MaxPairSumArray();
        System.out.println(sol.maxSum(new int[]{51, 71, 17, 24, 42})); // 88
        System.out.println(sol.maxSum(new int[]{1, 2, 3, 4})); // -1
    }
}
