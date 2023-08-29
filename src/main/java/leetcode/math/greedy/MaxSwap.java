package leetcode.math.greedy;

// https://leetcode.com/problems/maximum-swap/
public class MaxSwap {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int n = digits.length;

        // For each digit, check if there's a larger digit on the right
        for (int i = 0; i < n - 1; i++) {
            // find the maximum digit on the right of current digit
            char maxRight = '0';
            int maxRightIndex = -1;

            for (int j = n - 1; j > i; j--) {
                if (digits[j] > digits[i] && digits[j] > maxRight) {
                    maxRight = digits[j];
                    maxRightIndex = j;
                }
            }

            // If there's a bigger digit on the right of current digit, swap them
            if (maxRightIndex != -1) {
                char temp = digits[i];
                digits[i] = digits[maxRightIndex];
                digits[maxRightIndex] = temp;
                return Integer.parseInt(new String(digits));
            }
        }

        // If no swap was done
        return num;
    }
}
