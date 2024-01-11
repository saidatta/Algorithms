package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/#/description
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 * Created by venkatamunnangi on 3/27/17.
 */
public class MeetingRoomsII {

    public int minMeetingRooms2(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int start : starts) {
            if (start < ends[endsItr]) {
                rooms++;
            } else {
                endsItr++;
            }
        }
        return rooms;
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

        // Use a min meetingRoomsHeap to track the minimum end time of merged intervals
        PriorityQueue<Interval> meetingRoomsHeap = new PriorityQueue<>(
                intervals.length,
                Comparator.comparingInt(a -> a.end)
        );

        // start with the first meeting, put it to a meeting room
        meetingRoomsHeap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) { //
            // get the meeting room that finishes earliest
            Interval interval = meetingRoomsHeap.poll();

            if (intervals[i].start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals[i].end;
            } else {
                // otherwise, this meeting needs a new room
                meetingRoomsHeap.offer(intervals[i]);
            }

            // don't forget to put the meeting room back
            // and also let the heapify get triggered as well.
            meetingRoomsHeap.offer(interval);
        }

        return meetingRoomsHeap.size();
    }

    public int minMeetingRooms(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort the intervals by end time
        Arrays.sort(end, Comparator.comparingInt(a -> a));

        // Sort the intervals by start time
        Arrays.sort(start, Comparator.comparingInt(a -> a));

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }


    public static void main(String[] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();

        Interval interval1 = new Interval(0, 30);
        Interval interval2 = new Interval(5, 10);
        Interval interval3 = new Interval(15, 25);

        Interval[] intervals = new Interval[]{interval1, interval2, interval3};
        System.out.println(meetingRoomsII.minMeetingRooms2(intervals));
    }
}
