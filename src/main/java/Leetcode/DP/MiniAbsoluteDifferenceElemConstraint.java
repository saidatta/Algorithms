package Leetcode.DP;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

// https://leetcode.com/problems/minimum-absolute-difference-between-elements-with-constraint/
public class MiniAbsoluteDifferenceElemConstraint {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        if (x == 0) {
            return 0;
        }
        int n = nums.size();
        int ans = Integer.MAX_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = x; i < n && ans != 0; i++) {
            set.add(nums.get(i - x));
            Integer ceil = set.ceiling(nums.get(i));
            if (ceil != null) {
                ans = Math.min(ans, Math.abs(nums.get(i) - ceil));
            }
            Integer floor = set.floor(nums.get(i));
            if (floor != null) {
                ans = Math.min(ans, Math.abs(floor - nums.get(i)));
            }

        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        MiniAbsoluteDifferenceElemConstraint solution = new MiniAbsoluteDifferenceElemConstraint();
    }

}
