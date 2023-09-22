package leetcode.array.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 */
class NonOverlappingWindows {

    /**
     * Calculate the number of overlapping intervals that need to be removed
     * to make the rest of the intervals non-overlapping.
     *
     * @param intervals 2D array of intervals.
     * @return The number of intervals to be removed.
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // Sort intervals based on their end time
        sortIntervalsByEndTime(intervals);

        return countOverlappingIntervals(intervals);
    }

    /**
     * Sorts intervals based on their end times.
     *
     * @param intervals 2D array of intervals.
     */
    private void sortIntervalsByEndTime(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
    }

    /**
     * Count the number of overlapping intervals.
     *
     * @param intervals 2D array of sorted intervals.
     * @return The number of overlaps.
     */
    private int countOverlappingIntervals(int[][] intervals) {
        int end = Integer.MIN_VALUE;
        int overlaps = 0;

        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                end = interval[1];
            } else {
                overlaps++;
            }
        }

        return overlaps;
    }
}
