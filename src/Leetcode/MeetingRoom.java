package Leetcode;

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

        all.sort((o1, o2) -> {
            return o1.start - o2.start;
        });

        for(int i = 1; i< intervals.length; i++) {
            if(intervals[i].start < intervals[i-1].end) {
                return false;
            }
        }
        return true;
    }
}
