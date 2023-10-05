package leetcode.array.binarysearch;

// https://leetcode.com/problems/binary-search/description/
class BinarySearch {
    /**
     * Implements binary search to find the target in a sorted array.
     *
     * @param nums The sorted array.
     * @param target The target value to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid potential overflow

            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                left = mid + 1; // Target is in the right half
            } else {
                right = mid - 1; // Target is in the left half
            }
        }

        return -1; // Target was not found in the array
    }
}
