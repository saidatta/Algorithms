package Leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/merge-intervals/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (hasOverlap(interval.start, end)) {
                end = Math.max(end, interval.end);
            } else {
                // add the merged intervals now.
                result.add(new Interval(start, end));

                // now move to the new window.
                start = interval.start;
                end = interval.end;
            }
        }

        result.add(new Interval(start, end));
        return result;
    }

    private boolean hasOverlap(int start, int end) {
        return start <= end;
    }

    public static void main(String [] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        Interval [] inters = {new Interval(94133, 94133), new Interval(94200, 94299),new Interval(94226, 94399)};
        System.out.println(mergeIntervals.merge(Arrays.asList(inters)));
    }


    public class MyComparator implements Comparator<Integer>
    {
        public int compare( Integer x, Integer y )
        {
            return y - x;
        }
    }
}
