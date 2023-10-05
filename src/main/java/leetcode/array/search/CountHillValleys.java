package leetcode.array.search;

// https://leetcode.com/problems/count-hills-and-valleys-in-an-array/description/
public class CountHillValleys {

    /**
     * Counts the number of hills and valleys in the given array.
     *
     * @param nums The array of integers.
     * @return The number of hills and valleys.
     */
    public int countHillValley(int[] nums) {
        int hillValleyCount = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            boolean isHill = nums[i] > nums[i - 1] && nums[i] > nums[i + 1];
            boolean isValley = nums[i] < nums[i - 1] && nums[i] < nums[i + 1];

            // If the current index is a hill or valley
            if (isHill || isValley) {
                hillValleyCount++;
            }

            // If the current index and next index are equal,
            // assign the previous value to the current index
            if (nums[i] == nums[i + 1]) {
                nums[i] = nums[i - 1];
            }
        }

        return hillValleyCount;
    }
}
