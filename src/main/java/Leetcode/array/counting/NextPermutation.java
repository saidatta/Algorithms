package Leetcode.array.counting;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 */
public class NextPermutation {
//    Input: nums = [1,2,3]
//    Output: [1,3,2]

//    1. Traverse the array from the right.
//    2. If we find a pair of adjacent elements, nums[i] and nums[i-1], such that nums[i] > nums[i-1], we stop the
//    traversal.
//    3. If such a pair is found, we swap nums[i-1] and the next greater element on the right of nums[i-1]. If no such
//    pair is found, the array is already the largest permutation, so we simply reverse it to get the smallest
//    permutation.
//    4. Reverse the rest of the array after position i-1.
    public void nextPermutation(int[] nums) {
        int end = nums.length - 2;
        // Find the first decreasing element
        while (end >= 0 && nums[end + 1] <= nums[end]) {
            end--;
        }

        if (end >= 0) {
            int start = nums.length - 1;
            // Find the next largest element to swap with
            while (start >= 0 && nums[start] <= nums[end]) {
                start--;
            }

            swap(nums, start, end);
        }
        // Reverse the tail of the array
        reverse(nums, end + 1);
    }

    protected void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    protected void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();
        int[] nums = {1, 2, 3};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}

