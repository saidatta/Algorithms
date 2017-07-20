package Leetcode.DP;

/**
 * Created by venkatamunnangi on 3/24/17.
 */
public class RangeSumQueryImmutable {
    int[] nums;

    public RangeSumQueryImmutable(int[] nums) {
        for(int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];

        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if(i == 0) {
            return nums[j];
        }

        return nums[j] - nums[i - 1];
    }
}
