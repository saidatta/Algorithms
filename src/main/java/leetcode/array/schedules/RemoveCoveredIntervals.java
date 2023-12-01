package leetcode.array.schedules;

import java.util.Arrays;

// https://leetcode.com/problems/remove-covered-intervals/
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort the intervals. First by the start point, and then by the end point in descending order.
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));

        int remainingIntervals = 0;
        int end = 0;

        for (int[] interval : intervals) {
            // If the current interval is not covered by the previous interval
            if (interval[1] > end) {
                remainingIntervals++; // This interval is not covered
                end = interval[1];   // Update the end point
            }
        }

        return remainingIntervals;
    }

    public static void main(String[] args) {
        RemoveCoveredIntervals solution = new RemoveCoveredIntervals();
        System.out.println(solution.removeCoveredIntervals(new int[][]{{1,4}, {3,6}, {2,8}})); // Output: 2
        System.out.println(solution.removeCoveredIntervals(new int[][]{{1,4}, {2,3}})); // Output: 1
    }
}
