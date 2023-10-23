package leetcode.dp;

/**
 * Problem URL: https://leetcode.com/problems/decode-ways/
 *
 * Given an encoded message containing digits, determine the total number
 * of ways to decode it.
 *
 * https://www.youtube.com/watch?v=6aEyTjOwlJU
 * if you visualize it with a decision tree, the dp problem is easier
 */
public class DecodeWays {

    private static final int NUM_LETTERS = 26;

    /**
     * Recursive approach to find number of decodings for a given string.
     * This method has high time complexity due to multiple redundant calculations.
     */
    public int numDecodingsRecursion(String s) {
        return decodeHelper(s);
    }

    private int decodeHelper(String s) {
        int length = s.length();

        // Base cases
        if (length == 0) {
            return 0;
        } else if (length == 1) {
            return 1;
        }

        int ways = 0;

        if (Integer.parseInt(s.substring(0, 2)) <= NUM_LETTERS) {
            ways += decodeHelper(s.substring(2));
        }

        ways += decodeHelper(s.substring(1));

        return ways;
    }

    /**
     * Dynamic programming approach to efficiently find the number of decodings.
     */
    public int numDecodings(String s) {
        int length = s.length();

        if (length == 0) {
            return 0;
        }

        // Using an array to store the number of ways to decode the string up to that point.
        int[] dp = new int[length + 1];

        dp[length] = 1;
        dp[length - 1] = s.charAt(length - 1) != '0' ? 1 : 0;

        // Bottom-up computation of number of ways to decode the string.
        for (int i = length - 2; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] = (Integer.parseInt(s.substring(i, i + 2)) <= NUM_LETTERS)
                        ? dp[i + 1] + dp[i + 2] : dp[i + 1];
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        DecodeWays decoder = new DecodeWays();

        System.out.println("Using Recursion:");
        System.out.println(decoder.numDecodingsRecursion("111"));  // Expected: 3

        System.out.println("\nUsing Dynamic Programming:");
        System.out.println(decoder.numDecodings("00"));        // Expected: 0
        System.out.println(decoder.numDecodings("111"));       // Expected: 3
        System.out.println(decoder.numDecodings("14122"));     // Expected: 4
        System.out.println(decoder.numDecodings("12"));        // Expected: 2
    }
}
