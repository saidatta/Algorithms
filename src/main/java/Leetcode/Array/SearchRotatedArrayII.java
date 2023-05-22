package Leetcode.Array;

/**
 * what if duplicates are allowed in a rotate array
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class SearchRotatedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[left] < nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[left] > nums[mid]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                // if the left boundary has duplicates then increment it.
                left++;
            }
        }
        return false;
    }
}
