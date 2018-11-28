package CC;

import java.util.Arrays;


/***
 * This problem was asked by Airbnb.

 Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

 For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.

 Follow-up: Can you do this in O(N) time and constant space?
 */
public class NonAdjSum {

    public static void main(String [] args) {
        System.out.println(sumNonAdjacent(5,1,1,5));
        System.out.println(sumNonAdjacent(2, 4, 6, 2, 5));
    }

    public static int sumNonAdjacent(int... nums) {

        int[] dp = new int[2];

        dp[0] =nums[0];
        dp[1] = nums[1];


        for ( int i = 2; i < nums.length; i++) {
            int temp = dp[1];
            dp[1] = dp[0] + nums[i];
            dp[0] = Math.max(dp[0], temp);
            System.out.format("dp - %s, current number - %s, index - %s; ", Arrays.toString(dp), nums[i], i);
        }

        return Math.max(dp[0], dp[1]);
    }
}
