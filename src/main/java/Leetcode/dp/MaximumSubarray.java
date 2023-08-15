package Leetcode.dp;

import static java.lang.System.*;

/**
 * Leetcode 53
 * https://leetcode.com/problems/maximum-subarray/
 *
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 *
 * Created by venkatamunnangi on 2/7/17.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int [] netSums = new int[nums.length];


        // -2
        // -2 1
        // -2 1 -2
        // -2 1 -2 4
        // ... 4 3
        // ... 4 3 5
        // ... 4 3 5 7
        for(int i = 1; i < nums.length; i++) {
            netSums[i] = nums[i] + (Math.max(netSums[i - 1], 0));
            max = Math.max(max, netSums[i]);
        }

        return max;
    }

    public static void main(String [] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        out.println(maximumSubarray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
