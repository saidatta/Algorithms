package leetcode.array.prefixsum;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 * Created by venkatamunnangi on 3/24/17.
 */
public class RangeSumQueryImmutable {
    private final int[] prefixSum;

    public RangeSumQueryImmutable(int[] nums) {
        int n = nums.length;
        prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return prefixSum[right];
        } else {
            return prefixSum[right] - prefixSum[left - 1];
        }
    }
}