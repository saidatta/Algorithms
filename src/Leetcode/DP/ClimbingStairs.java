package Leetcode.DP;

/**
 * https://leetcode.com/problems/climbing-stairs/#/description
 *
 * Created by venkatamunnangi on 3/1/17.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] total = new int[n + 1];

        if(n == 0 || n == 1 || n == 2) {
            return n;
        }

        total[0] = 0;
        total[1] = 1;
        total[2] = 2;

        for(int i = 3; i<=n;i++) {
            // fibonacci sequence variation.
            total[i] = total[i-1] + total[i-2];
        }

        return total[n];
    }
}
