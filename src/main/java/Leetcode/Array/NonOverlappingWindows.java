package Leetcode.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class NonOverlappingWindows {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Interval[] convertedIntervals = new Interval[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            convertedIntervals[i] = new Interval(intervals[i][0], intervals[i][1]);
        }


        Arrays.sort(convertedIntervals, Comparator.comparingInt(a -> a.end));

        int end = Integer.MIN_VALUE;
        int res = 0;

        for (Interval interval : convertedIntervals) {
            if (interval.start >= end) {
                end = interval.end;
            } else {
                res++;
            }
        }
        return res;
    }

    class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
