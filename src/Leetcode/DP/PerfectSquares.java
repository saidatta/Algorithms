package Leetcode.DP;

import java.util.Arrays;

/**
 *
 * https://leetcode.com/problems/perfect-squares/?tab=Description
 *
 * Created by venkatamunnangi on 3/3/17.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 1; i + j * j <= n; j++){
                dp[i  + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String [] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(5));
    }
}
