package leetcode.array;

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
            int prevValue = x - 1;
            if(!set.contains( prevValue )) {
                // start of seq.
                int nextElem = x + 1;
                while(set.contains(nextElem)) {
                    nextElem++;
                }
                // 1 2 3 4
                best = Math.max(best, nextElem - x);
            }
        }

        return best;
    }
}
