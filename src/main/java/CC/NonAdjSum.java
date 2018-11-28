package CC;

import java.util.Arrays;

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
