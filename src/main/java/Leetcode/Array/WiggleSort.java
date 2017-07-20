package Leetcode.Array;

/**
 * https://leetcode.com/problems/wiggle-sort/#/description
 * <p>
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 * <p>
 * Created by venkatamunnangi on 5/10/17.
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 1) {
                // oodds are in ascending
                if (nums[i - 1] > nums[i]) {
                    // descending order then swap.
                    swap(nums, i);
                }
            } else if (i != 0 && nums[i - 1] < nums[i]) {
                // even numbers are in descending.
                // ascending order then swap.
                swap(nums, i);
            }
        }
    }

    public void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }
}
