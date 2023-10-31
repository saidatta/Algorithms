package leetcode.dp.array;

// https://leetcode.com/problems/combination-sum-iv/description/
public class CombinationSumIV {

//   dp[i] value will represent the number of ways to make the sum i using the given numbers. We'll iterate through
//   each number and add the value of dp[i - num]
//   i.e How many ways we can create the current value minus the number to dp[i].
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // There's one way to form the sum 0: by using no numbers.
        dp[0] = 1;

        for (int currentSum = 1; currentSum <= target; currentSum++) {
            for (int num : nums) {
                if (currentSum - num >= 0) {
                    dp[currentSum] += dp[currentSum - num];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int target1 = 4;

        int[] nums2 = {9};
        int target2 = 3;

        System.out.println(combinationSum4(nums1, target1));  // Output: 7
        System.out.println(combinationSum4(nums2, target2));  // Output: 0
    }

//    Follow up:
//
//    If negative numbers are allowed in the array, the problem becomes more complicated since there might be infinite
//    combinations. For instance, if we have the numbers -1, 1, and 2 and a target of 2, we can achieve the target with
//    (1, 1, -1, 1, 1, -1, 2), (1, -1, 1, 1, -1, 2, 1, -1, 1), etc. This can go on infinitely.
//
//    To tackle the situation with negative numbers, we would need an additional constraint, such as a limitation on the
//    number of numbers we can use. Otherwise, there could be infinite combinations, which would make it impossible to
//    give a definitive count.

}
