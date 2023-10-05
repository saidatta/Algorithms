package leetcode.array.search;

import java.util.Arrays;

//This problem can be directly solved using
//
//        two pointer approach
//        binary search.
//        But we need to sort the array to perform either of two approaches.
//
//        If the interviewer asks us to maintain the original array or get the result in liner time (while sorting
//        takes nlogn time), we have to move forward with Counting sort approach.
public class TwoSumLessThanKFollowUp {
    public int twoSumLessThanK(int[] nums, int k) {
        int[] count = new int[1001];
        int maxResult = 0;
        int left = 1, right = 1000;

        for (int num : nums) {
            count[num]++;
        }

        while (left < right) {
            while (count[right] == 0) {
                right--;
            }
            while (count[left] == 0) {
                left++;
            }
            if (right < left) {
                break;
            }
            if (left == right ) {
                if (count[left] > 1 && (left + right < k)) {
                    maxResult = Math.max(maxResult, (left + right));
                }
                left++;
            }
            else {
                if (left + right >= k) {
                    right--;
                }  else if (left + right < k){
                    maxResult = Math.max(maxResult, (left + right));
                    left++;
                }
            }
        }
        return maxResult == 0 ? -1 : maxResult;
    }

    // Followup - allow negative numbers
    public int twoSumLessThanK2(int[] nums, int K) {
        // Step 1: Sort the given array
        Arrays.sort(nums);

        // Initialize two pointers, left and right
        int left = 0;
        int right = nums.length - 1;

        // Initialize maxSum to a value that's smaller than any possible sum
        int maxSum = Integer.MIN_VALUE;

        // Step 2: Use two-pointer technique to find the maximum sum
        while (left < right) {
            int currentSum = nums[left] + nums[right];

            if (currentSum < K) {
                // If current sum is less than K, update maxSum and move left pointer to the right
                maxSum = Math.max(maxSum, currentSum);
                left++;
            } else {
                // If current sum is equal or greater than K, move right pointer to the left
                right--;
            }
        }

        // Step 3: Check if we found a valid sum, if not return -1
        if (maxSum == Integer.MIN_VALUE) {
            return -1;
        }
        return maxSum;
    }

}
