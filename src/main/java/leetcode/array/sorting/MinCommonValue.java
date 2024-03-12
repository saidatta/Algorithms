package leetcode.array.sorting;

public class MinCommonValue {
    public static int findMinimumCommonValue(int[] nums1, int[] nums2) {
        // Initialize two pointers for both arrays
        int i = 0, j = 0;

        // Iterate through both arrays as long as neither pointer reaches the end
        while (i < nums1.length && j < nums2.length) {
            // If a common value is found, return it immediately
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            }
            // Otherwise, move the pointer of the smaller value to find a potential match
            else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        // If we reach the end of either array without finding a common value, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4};
        System.out.println(findMinimumCommonValue(nums1, nums2)); // Output: 2

        int[] nums1Example2 = {1, 2, 3, 6};
        int[] nums2Example2 = {2, 3, 4, 5};
        System.out.println(findMinimumCommonValue(nums1Example2, nums2Example2)); // Output: 2
    }

    public int getCommon(int[] nums1, int[] nums2) {
        // Binary search should be done on the larger array
        // If nums1 is longer, call getCommon with the arrays swapped
        if (nums1.length > nums2.length) {
            return getCommon(nums2, nums1);
        }

        // Search for each element of nums1 in nums2
        // Return the first common element found
        for (int num : nums1) {
            if (binarySearch(num, nums2)) {
                return num;
            }
        }

        // Return -1 if there are no common elements
        return -1;
    }

    private boolean binarySearch(int target, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
