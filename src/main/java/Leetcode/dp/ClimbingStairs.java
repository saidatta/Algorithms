package Leetcode.dp;

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

            // Since we can only get to the 4th step by getting to the 3rd step and going up by one, or
            // by getting to the 2nd step and going up by two. So f(4) = f(3) + f(2).

            for(int i = 3; i<=n;i++) {
                // fibonacci sequence variation.
                total[i] = total[i-1] + total[i-2];
            }

            return total[n];
        }
}
