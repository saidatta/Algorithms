package leetcode;

import java.util.Arrays;

/**
 * Created by venkatamunnangi on 12/15/16.
 */
public class SearchForRange {
    public int[] searchRange(int[] nums, int target) {
        return helper(nums, target, 0, nums.length - 1);
    }

    private int[] helper(int[] nums, int target, int lo, int hi) {
        if (nums[lo] == target && nums[hi] == target) {
            //base case found value.
            return new int[]{lo, hi};
        }

        if (nums[lo] <= target && nums[hi] >= target) {
            int mid = lo + (hi - lo) / 2;
            int[] left = helper(nums, target, lo, mid);
            int[] right = helper(nums, target, mid + 1, hi);
            if (left[0] == -1) return right;
            if (right[0] == -1) return left;
            return new int[]{left[0], right[1]};
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        SearchForRange searchForRange = new SearchForRange();
        System.out.println(Arrays.toString(searchForRange.searchRange(new int[]{1,1,1,1,2}, 1)));
        System.out.println(Arrays.toString(searchForRange.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));


    }
}
