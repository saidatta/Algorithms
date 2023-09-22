package leetcode.array.traversal;

import java.util.TreeSet;

// https://leetcode.com/problems/contains-duplicate-iii/description/
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length == 0 || indexDiff <= 0) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // Find the successor of current element
            Long floor = set.floor((long) nums[i] + valueDiff);
            if (floor != null && floor >= (long) nums[i] - valueDiff) {
                return true;
            }

            set.add((long) nums[i]);
            if (i >= indexDiff) {
                set.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}
