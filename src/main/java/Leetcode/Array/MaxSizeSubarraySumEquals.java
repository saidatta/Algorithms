package Leetcode.Array;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/#/description
 *
 * Created by venkatamunnangi on 3/23/17.
 */
public class MaxSizeSubarraySumEquals {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> index = new HashMap<>();
        index.put(0, -1);
        int sum = 0, max = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, i - index.getOrDefault(sum - k, i));

            index.putIfAbsent(sum, i);
        }
        return max;
    }

    public static void main(String [] args) {
        MaxSizeSubarraySumEquals maxSizeSubarraySumEquals = new MaxSizeSubarraySumEquals();
        int[] arr = {1,-1,5,-2,3};
        out.println(maxSizeSubarraySumEquals.maxSubArrayLen(arr, 3));

        int[] arr2 = {-2, -1, 2, 1};
        out.println(maxSizeSubarraySumEquals.maxSubArrayLen(arr2, 1));
    }
}
