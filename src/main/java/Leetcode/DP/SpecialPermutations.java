package Leetcode.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/special-permutations/
 */
public class SpecialPermutations {
    int MODULUS = 1_000_000_007;

    public  int specialPerm(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[][] dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            dp[i][1 << i] = 1;
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) > 0) {
                    for (int j = 0; j < n; j++) {
                        if ((mask & (1 << j)) == 0 && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                            dp[j][mask | (1 << j)] += dp[i][mask];
                            dp[j][mask | (1 << j)] %= MODULUS;
                        }
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += dp[i][(1 << n) - 1];
            result %= MODULUS;
        }
        return result;
    }
}
