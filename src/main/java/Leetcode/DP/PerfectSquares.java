package Leetcode.DP;

/**
 * https://leetcoqde.com/problems/perfect-squares/?tab=Description
 * <p>
 * Created by venkatamunnangi on 3/3/17.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        //creating a new table
        int[] dp = new int[n + 1];

        int x = n < 3 ? n : 3;

        for (int i = 0; i <= x; i++) {
            dp[i] = i;
        }

        //calculating min square from 4 to n.
        for (int i = 4; i <= n; i++) {
            //starting point to be high enough for min.
            dp[i] = i;
            for (int j = 1; j <= i; j++) {
                int temp = j * j;
                if (i - temp < 0) {
                    break;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - temp]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(5));
    }
}
