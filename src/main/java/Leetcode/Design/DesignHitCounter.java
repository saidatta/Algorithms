package Leetcode.Design;

/**
 * https://leetcode.com/problems/design-hit-counter/#/description
 *
 * Created by venkatamunnangi on 7/22/17.
 */
public class DesignHitCounter {
    private int[] hits;
    private int[] timeStamp;

    /** Initialize your data structure here. */
    public DesignHitCounter() {
        hits = new int[300];
        timeStamp = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;

        if(timeStamp[index] != timestamp) {
            timeStamp[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for(int i = 0; i < 300; i++) {
            if(timestamp - timeStamp[i] < 300) {
                total += hits[i];
            }
        }

        return total;
    }
}
