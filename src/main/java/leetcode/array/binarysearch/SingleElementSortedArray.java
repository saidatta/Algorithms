package leetcode.array.binarysearch;

// https://leetcode.com/problems/single-element-in-a-sorted-array/
public class SingleElementSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // Check if the middle element is part of the first pair or the second pair
            boolean halvesAreEven = (high - mid) % 2 == 0;

            if (nums[mid] == nums[mid + 1]) {
                if (halvesAreEven) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] == nums[mid - 1]) {
                if (halvesAreEven) {
                    high = mid - 2;
                } else {
                    low = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }

        return nums[low];
    }

    // Main method for testing
    public static void main(String[] args) {
        SingleElementSortedArray solution = new SingleElementSortedArray();
        System.out.println("Single element in [1,1,2,3,3,4,4]: " + solution.singleNonDuplicate(new int[]{1,1,2,2,3,3,4,4,8})); // Expected: 2
        System.out.println("Single element in [3,3,7,7,10,11,11]: " + solution.singleNonDuplicate(new int[]{3,3,7,7,10,11,11})); // Expected: 10
    }
}
