package leetcode.array.traversal;

public class MaxConsecutiveOnesII {
    /**
     * Find the maximum number of consecutive 1s in the given binary array.
     * @param nums A binary array.
     * @return The maximum number of consecutive 1s.
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int zeroCount = 0; // to track number of zeros in the current window

        for (int left = 0, right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Ensure only one zero is allowed in the current window
            while (zeroCount == 2) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maxCount with the current window size
            maxCount = Math.max(maxCount, right - left + 1);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesII solution = new MaxConsecutiveOnesII();

        int[] testArray = {1, 0, 1, 1, 0, 1};
        int result = solution.findMaxConsecutiveOnes(testArray);
        System.out.println("Max consecutive ones: " + result);  // Expected output: 4
    }
}
