package Leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } else {
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        result.add(new Interval(start, end));
        return result;
    }

    public static void main(String [] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        Interval [] inters = {new Interval(94133, 94133), new Interval(94200, 94299),new Interval(94226, 94399)};
        System.out.println(mergeIntervals.merge(Arrays.asList(inters)));
    }
}
