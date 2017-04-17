package Leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *  https://leetcode.com/problems/meeting-rooms-ii/#/description
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 * Created by venkatamunnangi on 3/27/17.
 */
public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length, (a, b) -> a.end - b.end);

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) { //
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            // and also let the heapify get triggered as well.
            heap.offer(interval);
        }

        return heap.size();
    }

    public static void main(String [] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();

        Interval interval1 = new Interval(0, 30);
        Interval interval2 = new Interval(5, 10);
        Interval interval3 = new Interval(15,25);

        Interval[] intervals = new Interval[] {interval1, interval2, interval3};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals));
    }
}
