package leetcode.array.sorting;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
public class MaxPairEqualSumDigits {
    public int maximumSum(int[] nums) {
        Map<Integer, Integer> maxSumDigits = new HashMap<>();
        int maxSum = -1;

        for (int num : nums) {
            int sumDigits = sumOfDigits(num);
            if (maxSumDigits.containsKey(sumDigits)) {
                maxSum = Math.max(maxSum, maxSumDigits.get(sumDigits) + num);
                maxSumDigits.put(sumDigits, Math.max(maxSumDigits.get(sumDigits), num));
            } else {
                maxSumDigits.put(sumDigits, num);
            }
        }

        return maxSum;
    }

    private int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        MaxPairEqualSumDigits solution = new MaxPairEqualSumDigits();
        System.out.println(solution.maximumSum(new int[]{18,43,36,13,7})); // Output: 54
        System.out.println(solution.maximumSum(new int[]{10,12,19,14})); // Output: -1
    }
}
