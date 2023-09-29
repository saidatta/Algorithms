package leetcode.array;

/**
 * A solution for the problem: https://leetcode.com/problems/subarray-product-less-than-k/
 * The class provides a method to calculate the number of contiguous subarrays where the product of all the elements
 * in the subarray is less than k.
 */
public class SubarrayProductLessThanK {

    /**
     * Returns the count of subarrays that have a product less than k.
     *
     * @param nums Input array of integers.
     * @param k The threshold for product comparison.
     * @return The count of subarrays with a product less than k.
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }

        // To keep track of the number of valid subarrays.
        int subarrayCount = 0;
        // To keep track of the product of the current subarray.
        int productOfCurrentSubarray = 1;
        // Left pointer for the sliding window.
        int leftPointer = 0;

        for (int rightPointer = 0; rightPointer < nums.length; rightPointer++) {
            productOfCurrentSubarray *= nums[rightPointer];

            // Adjust the left pointer to keep the product under k.
            while (leftPointer <= rightPointer && productOfCurrentSubarray >= k) {
                productOfCurrentSubarray /= nums[leftPointer++];
            }

            // Add the number of subarrays ending at the current position.
            // this works because you're counting the index everytime it occurs in the sliding window
            // in below example, you first count 10, then you count 10 again when it is with 10, 5
            subarrayCount += rightPointer - leftPointer + 1;
        }

        return subarrayCount;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanK solution = new SubarrayProductLessThanK();
        int[] sampleInput = {10, 5, 2, 6};
        int threshold = 100;

        // Print the result for the sample input.
        System.out.println(solution.numSubarrayProductLessThanK(sampleInput, threshold));
    }
}

