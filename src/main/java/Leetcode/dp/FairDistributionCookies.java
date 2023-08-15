package Leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/fair-distribution-of-cookies/
 */

//  The main goal is to distribute all the cookies among 'k' children in such a way that unfairness (the maximum total
//  cookies obtained by a single child) is minimized.
//
//        This code first calculates the sum of all cookies in every possible subset of the cookie bags, using bit
//        manipulation to represent subsets. This is done in the first loop where mask iterates over all possible bit
//        representations from 0 to 2^n - 1 (where n is the number of bags), and s[mask] is the sum of cookies in the
//        subset represented by mask.
//
//        Applies dynamic programming to calculate the minimum unfairness. dp[i][j] represents the
//        minimum unfairness when distributing the cookies in subset j among i children. For each subset j and for
//        each child from 1 to k, it tries to find a submask (a subset of j) such that the maximum value between the
//        sum of cookies in this submask s[submask] and dp[i - 1][j ^ submask] (the minimum unfairness when distributing
//        the cookies in the remaining subset among i - 1 children) is minimized. This ensures that the cookies are
//        distributed evenly among the children, thereby minimizing the unfairness.
//
//        Finally, dp[k][n - 1] gives the minimum unfairness when distributing all the cookies among k children.
//
//        The code uses an optimization where it only tries to distribute the cookies among i children when the size of
//        the subset is at least i. This is why the inner loop in the DP part starts from k.
public class FairDistributionCookies {
    public int distributeCookies(int[] cookies, int k) {
        //  the number of subsets of a set with n elements is 2^n
        int n = 1 << cookies.length;
        int[] totalSumAcrossAllPossibleSubsets = new int[n + 1];

        for (int mask = 0; mask < n; mask++) {
            // each mask represents some form of subset.
            for (int i = 0; i < cookies.length; i++) {
                // get ith bit. this checks if the element is present in the current iteration of subset.
                if ((mask & (1 << i)) != 0) {
                    totalSumAcrossAllPossibleSubsets[mask] += cookies[i];
                }
            }
        }

        // # of children and total possible subsets.
        int[][] dp = new int[k + 1][n];
        for (int i = 0; i < k + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = k; j < n; j++) {
                for (Integer submask : submasks(j)) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(
                            totalSumAcrossAllPossibleSubsets[submask],
                            dp[i - 1][j ^ submask]));
                }
            }
        }
        return dp[k][n - 1];
    }

    public List<Integer> submasks(int mask) {
        List<Integer> res = new ArrayList<>();
        int submask = mask;
        while (submask > 0) {
            res.add(submask);
            submask = (submask - 1) & mask;
        }
        return res;
    }

    public static void main(String[] args) {
        // Creating an instance of the Solution class
        FairDistributionCookies sol = new FairDistributionCookies();

        // Define input parameters
        int[] cookies = {2,3,5,7};
        int k = 3;

        // Invoke distributeCookies method
        int result = sol.distributeCookies(cookies, k);

        // Print the result
        System.out.println("The minimum maximum number of cookies that can be distributed is: " + result);
    }
}

