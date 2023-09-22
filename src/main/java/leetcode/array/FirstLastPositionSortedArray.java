package leetcode.array;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by venkatamunnangi on 9/11/19.
 */
public class FirstLastPositionSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1}; // Default values if target is not found
        result[0] = findPosition(nums, target, true);
        result[1] = findPosition(nums, target, false);
        return result;
    }

    // Binary search to find either the first or last occurrence of the target
    // isFindFirst flag determines which one we're looking for
    private int findPosition(int[] nums, int target, boolean isFindFirst) {
        // Default value if target is not found
        int result = -1;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                // Update the result when found
                result = mid;
                // Move either left or right depending on what we're looking for
                if (isFindFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
