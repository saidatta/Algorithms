package leetcode.array.twoPointer;

// https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {
    /**
     * Calculates the amount of trapped rainwater given an array of heights.
     *
     * @param height Array representing the heights.
     * @return Amount of trapped rainwater.
     */
    public int trap(int[] height) {

        // Check for null or empty input
        if (height == null || height.length == 0) {
            return 0;
        }

        // Find the index of the highest bar in the array
        int highestBarIndex = findHighestBarIndex(height);

        // Calculate trapped water to the left of the highest bar
        int trappedWaterLeft = calculateTrappedWater(height, 0, highestBarIndex, true);

        // Calculate trapped water to the right of the highest bar
        int trappedWaterRight = calculateTrappedWater(height, height.length - 1, highestBarIndex, false);

        return trappedWaterLeft + trappedWaterRight;
    }

    /**
     * Finds the index of the highest bar in the array.
     *
     * @param height Array representing the heights.
     * @return Index of the highest bar.
     */
    private int findHighestBarIndex(int[] height) {
        int maxIndex = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[maxIndex] < height[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Calculates trapped water in one direction starting from a given index.
     *
     * @param height      Array representing the heights.
     * @param start       Starting index.
     * @param end         Ending index.
     * @param leftToRight Direction of calculation (true for left to right, false for right to left).
     * @return Amount of trapped rainwater in the specified direction.
     */
    private int calculateTrappedWater(int[] height, int start, int end, boolean leftToRight) {
        int trappedWater = 0;
        int prevHigh = height[start];

        // Modify iteration based on direction
        int step = leftToRight ? 1 : -1;

        for (int i = start + step; leftToRight ? i < end : i > end; i += step) {
            if (prevHigh < height[i]) {
                // new intermediate height is found, switch.
                prevHigh = height[i];
            }
            trappedWater += (prevHigh - height[i]);
        }

        return trappedWater;
    }
}
