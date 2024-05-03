package leetcode.array.counting;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/contains-duplicate/description/
public class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (!seen.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(containsDuplicate(nums1)); // true

        int[] nums2 = {1, 2, 3, 4};
        System.out.println(containsDuplicate(nums2)); // false

        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println(containsDuplicate(nums3)); // true
    }
}
