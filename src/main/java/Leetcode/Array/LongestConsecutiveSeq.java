package Leetcode.Array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Created by venkatamunnangi on 5/31/17.
 */
public class LongestConsecutiveSeq {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            set.add(i);
        }

        int best = 0;
        for (int x : nums) {
            if(!set.contains(x -1 )) {
                int y = x + 1;
                while(set.contains(y)) {
                    y++;
                }
                best = Math.max(best, y - x);
            }
        }

        return best;
    }
}
