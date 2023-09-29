package leetcode.dp.array;

import java.util.Arrays;

// https://leetcode.com/problems/target-sum/description/
public class TargetSum {

//    The idea behind this approach is as follows. Suppose we can find out the number of times a particular sum,
//    say sumisum_isum
//    is possible up to a particular index, say iii, in the given numsnumsnums array, which is given by say counticount_icount
//            i
//​
//        . Now, we can find out the number of times the sum sumi+nums[i]sum_i + nums[i]sum
//            i
//​
//        +nums[i] can occur easily as counticount_icount
//            i
//​
//        . Similarly, the number of times the sum sumi−nums[i]sum_i - nums[i]sum
//            i
//​
//        −nums[i] occurs is also given by counticount_icount
//    i
//​
//        .
//
//    Thus, if we know all the sums sumjsum_jsum
//    j
//​
//        's which are possible up to the jthj^{th}j
//    th
//    index by using various assignments, along with the corresponding count of assignments, countjcount_jcount
//            j
//​
//        , leading to the same sum, we can determine all the sums possible up to the (j+1)th(j+1)^{th}(j+1)
//    th
//    index along with the corresponding count of assignments leading to the new sums.
//
//    Based on this idea, we make use of a dpdpdp to determine the number of assignments which can lead to the given sum. dp[i][j]dp[i][j]dp[i][j] refers to the number of assignments which can lead to a sum of jjj up to the ithi^{th}i
//            th
//    index. To determine the number of assignments which can lead to a sum of sum+nums[i]sum + nums[i]sum+nums[i] up to the (i+1)th(i+1)^{th}(i+1)
//    th
//    index, we can use dp[i][sum+nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]dp[i][sum + nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum]dp[i][sum+nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]. Similarly, dp[i][sum−nums[i]]=dp[i][sum−nums[i]]+dp[i−1][sum]dp[i][sum - nums[i]] = dp[i][sum - nums[i]] + dp[i-1][sum]dp[i][sum−nums[i]]=dp[i][sum−nums[i]]+dp[i−1][sum]. We iterate over the dpdpdp array in a row-wise fashion, i.e., firstly, we obtain all the sums which are possible up to a particular index along with the corresponding count of assignments and then proceed for the next element (index) in the numsnumsnums array.
//
//    But, since the sumsumsum can range from -total to total, where total equals to the sum of the nums array, we need to add an offset of total to the sum indices (column number) to map all the sums obtained to positive range only.
//
//    At the end, the value of dp[n−1][S+total]dp[n-1][S+total]dp[n−1][S+total] gives us the required number of assignments. Here, nnn refers to the number of elements in the numsnumsnums array.
//
//    The animation below shows the way various sums are generated along with the corresponding indices. The example assumes sumsumsum values to lie in the range of -6 to +6 just for the purpose of illustration.
    public int findTargetSumWays(int[] nums, int targetSum) {
        // Calculate the total sum of the array
        int total = Arrays.stream(nums).sum();

        // Create a 2D DP table where dp[i][j] represents the number of ways
        // to achieve a sum of j using the first i numbers
        int[][] dp = new int[nums.length][2 * total + 1];

        // Initialize the base cases: there's one way to get the number itself
        // (by adding) and one way to get its negative (by subtracting)
        dp[0][nums[0] + total] = 1;
        dp[0][-nums[0] + total] += 1;

        // Iterate over all numbers
        for (int i = 1; i < nums.length; i++) {
            // For each possible sum from -total to total
            for (int sum = -total; sum <= total; sum++) {
                // If there are ways to get 'sum' using the first i-1 numbers
                if (dp[i - 1][sum + total] > 0) {
                    // Increment the ways to get 'sum + nums[i]' and 'sum - nums[i]'
                    // using the first i numbers
                    dp[i][sum + nums[i] + total] += dp[i - 1][sum + total];
                    dp[i][sum - nums[i] + total] += dp[i - 1][sum + total];
                }
            }
        }

        // If the absolute value of targetSum is greater than total,
        // there's no way to achieve it
        if (Math.abs(targetSum) > total) {
            return 0;
        }

        // Return the number of ways to get targetSum using all numbers
        return dp[nums.length - 1][targetSum + total];
    }
}
