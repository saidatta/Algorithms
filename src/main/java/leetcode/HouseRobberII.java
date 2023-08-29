package leetcode;

/**
 * https://leetcode.com/problems/house-robber-ii
 * Created by venkatamunnangi on 9/21/19.
 */
public class HouseRobberII {
    public int rob(int... nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }


    public int rob(int[] nums, int lo, int high) {
        if (nums == null) {
            return 0;
        }

        int previousEvenMaxIndex = 0, previousMaxOddIndex = 0;
        for (int i = lo; i <= high; i++) {
            if (i % 2 == 0) {
                previousEvenMaxIndex = Math.max(previousMaxOddIndex, previousEvenMaxIndex + nums[i]);
            } else {
                previousMaxOddIndex = Math.max(previousEvenMaxIndex, previousMaxOddIndex + nums[i]);
            }
        }

        return Math.max(previousEvenMaxIndex, previousMaxOddIndex);
    }

}
