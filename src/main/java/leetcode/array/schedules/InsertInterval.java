package leetcode.array.schedules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    /**
     * Check if two intervals overlap.
     *
     * @param a - first interval
     * @param b - second interval
     * @return - true if the intervals overlap, otherwise false
     */
    private boolean doesOverlap(int[] a, int[] b) {
        return Math.min(a[1], b[1]) - Math.max(a[0], b[0]) >= 0;
    }

    /**
     * Merge two overlapping intervals.
     *
     * @param a - first interval
     * @param b - second interval
     * @return - merged interval
     */
    private int[] mergeIntervals(int[] a, int[] b) {
        return new int[]{Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    /**
     * Find the position to insert the newInterval while maintaining the sorted order.
     *
     * @param intervals    - list of intervals
     * @param newInterval  - interval to be inserted
     * @return - the index at which the newInterval should be inserted
     */
    private int findInsertPosition(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return 0;
        }

        int start = 0, end = intervals.length - 1;
        int insertPos = intervals.length;

        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (intervals[mid][0] > newInterval[0]) {
                insertPos = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return insertPos;
    }

    /**
     * Insert the newInterval into the list of intervals.
     *
     * @param intervals    - list of intervals
     * @param newInterval  - interval to be inserted
     * @return - list of intervals after inserting newInterval
     */
    private int[][] insertAndSort(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
        int insertPos = findInsertPosition(intervals, newInterval);

        if (insertPos != intervals.length) {
            // in middle, so insert it at insertPos
            list.add(insertPos, newInterval);
        } else {
            // if insertPos is at the end, just append the interval.
            list.add(newInterval);
        }

        return list.toArray(new int[list.size()][2]);
    }

    /**
     * Insert the interval newInterval into the intervals list and merge if necessary.
     *
     * @param intervals    - original list of intervals
     * @param newInterval  - interval to be inserted
     * @return - merged list of intervals after inserting newInterval
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        intervals = insertAndSort(intervals, newInterval);

        List<int[]> mergedIntervals = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            // Keep merging as long as the intervals overlap.
            while (i < intervals.length && doesOverlap(currentInterval, intervals[i])) {
                currentInterval = mergeIntervals(currentInterval, intervals[i]);
                i++;
            }

            // last i increment doesnt overlap, hence while loop exits. so, reverting it.
            i--;
            mergedIntervals.add(currentInterval);
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }

    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();

//        List<int[]> intervals = List.of(new int[]{1, 3}, new int[]{6, 9});
//        int[] newInterval = {2, 5};
//        System.out.println(solution.insert(intervals, newInterval)); // [[1, 5], [6, 9]]
//
//        intervals = List.of(new int[]{1, 2}, new int[]{3, 5}, new int[]{6, 7}, new int[]{8, 10}, new int[]{12, 16});
//        newInterval = new int[]{4, 8};
//        System.out.println(solution.insert(intervals, newInterval)); // [[1, 2], [3, 10], [12, 16]]
    }
}
