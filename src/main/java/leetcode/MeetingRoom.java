package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/meeting-rooms/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class MeetingRoom {
    public boolean canAttendMeetings(Interval[] intervals) {
        List<Interval> all = Arrays.asList(intervals);

        all.sort((o1, o2) -> o1.start - o2.start);

        for(int i = 1; i< intervals.length; i++) {
            if(intervals[i].start < intervals[i-1].end) {
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        for(int i = 1; i< intervals.length; i++) {
            if(intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }
        return true;
    }
}
