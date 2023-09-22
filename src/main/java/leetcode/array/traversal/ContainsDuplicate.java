package leetcode.array.traversal;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x: nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }
}
