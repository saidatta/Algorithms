package leetcode.dp.array;

/**
 * https://leetcode.com/problems/house-robber-ii
 * Created by venkatamunnangi on 9/21/19.
 */
public class HouseRobberII {

    /**
     * Determines the maximum amount you can rob from circular houses without robbing two adjacent houses.
     * @param nums Amounts of money in each house.
     * @return The maximum amount of money you can rob.
     */
    public int rob(int... nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // Two scenarios: 1. Include first house, exclude last house
        //                2. Exclude first house, include last house
        return Math.max(robLinear(nums, 0, nums.length - 2), robLinear(nums, 1, nums.length - 1));
    }

    /**
     * Helper function to determine the maximum amount to rob from a linear configuration of houses.
     * @param nums Amounts of money in each house.
     * @param start Starting index of the house to consider.
     * @param end Ending index of the house to consider.
     * @return The maximum amount of money you can rob from houses between start and end inclusive.
     */
    private int robLinear(int[] nums, int start, int end) {
        int robPrevious = 0;    // Maximum amount robbed from the previous house.
        int robCurrent = 0;     // Maximum amount robbed up to the current house.

        for (int i = start; i <= end; i++) {
            int temp = robCurrent;
            robCurrent = Math.max(robCurrent, robPrevious + nums[i]);
            robPrevious = temp;
        }

        return robCurrent;
    }
}
