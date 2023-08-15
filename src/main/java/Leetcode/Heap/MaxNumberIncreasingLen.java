package Leetcode.Heap;

import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/
 */
public class MaxNumberIncreasingLen {
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        Collections.sort(usageLimits);

        long total = 0;
        int maxGroups    = 0;
        for (int limit : usageLimits) {
            total += limit;
            if (total >= ((long)maxGroups + 1) * ((long)maxGroups + 2) / 2) {
                maxGroups++;
            }
        }

        return maxGroups;
    }
}
