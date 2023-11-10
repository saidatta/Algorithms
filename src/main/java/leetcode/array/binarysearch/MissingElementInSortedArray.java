package leetcode.array.binarysearch;

// https://leetcode.com/problems/missing-element-in-sorted-array/description/
public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        // If the kth missing number is greater than the last element of the array
        if (k > missing(n - 1, nums)) {
            return nums[n - 1] + k - missing(n - 1, nums);
        }

        int left = 0, right = n - 1;
        // Binary search to find the smallest index such that the number of missing numbers
        // until that index is greater than or equal to k
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (missing(mid, nums) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // At this point, left is the smallest index such that the number of missing
        // numbers until that index is greater than or equal to k
        // The kth missing number is therefore one step before left, plus the remaining k
        return nums[left - 1] + k - missing(left - 1, nums);
    }

    // Helper function to count the number of missing elements until index idx
    private int missing(int idx, int[] nums) {
        return nums[idx] - nums[0] - idx;
    }

    public static void main(String[] args) {
        MissingElementInSortedArray solution = new MissingElementInSortedArray();

        // Example 1
        int[] nums1 = {4, 7, 9, 10};
        int k1 = 1;
        System.out.println("Example 1: " + solution.missingElement(nums1, k1)); // Output: 5

        // Example 2
        int k2 = 3;
        System.out.println("Example 2: " + solution.missingElement(nums1, k2)); // Output: 8

        // Example 3
        int[] nums3 = {1, 2, 4};
        int k3 = 3;
        System.out.println("Example 3: " + solution.missingElement(nums3, k3)); // Output: 6
    }
}

