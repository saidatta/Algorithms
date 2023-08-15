package Leetcode.array;

/**
 * https://leetcode.com/problems/max-consecutive-ones/#/description
 *
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * Google
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int max = 0, count = 0;
        for(int i : nums) {
            if(i == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }

        return Math.max(max, count);
    }
}
