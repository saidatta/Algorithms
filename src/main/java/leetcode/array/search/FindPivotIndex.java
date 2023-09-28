package leetcode.array.search;

public class FindPivotIndex {
    /**
     * Finds the pivot index in the array. The pivot index is the index
     * where the sum of the numbers to its left is equal to the sum of
     * numbers to its right.
     *
     * @param nums Input array of integers.
     * @return Pivot index or -1 if it doesn't exist.
     */
    public int pivotIndex(int[] nums) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        // Iterate over the array and check if current index is the pivot
        for (int i = 0; i < nums.length; i++) {
            // Calculate the right sum by subtracting left sum and current element from total
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;  // Found the pivot index
            }

            leftSum += nums[i];
        }

        return -1;  // Pivot index not found
    }
}
