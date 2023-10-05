package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/meeting-rooms/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class MeetingRoom {
    public boolean canAttendMeetings(Interval[] intervals) {
        List<Interval> all = Arrays.asList(intervals);

        all.sort(Comparator.comparingInt(o -> o.start));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        // Sort intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int i = 1; i < intervals.length; i++) {
            // Check for overlap
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        return true;
    }

}
