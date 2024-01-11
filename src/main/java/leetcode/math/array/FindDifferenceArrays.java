package leetcode.math.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/find-the-difference-of-two-arrays/description/
public class FindDifferenceArrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // Add all elements of nums1 to set1
        for (int num : nums1) {
            set1.add(num);
        }

        // Add all elements of nums2 to set2
        for (int num : nums2) {
            set2.add(num);
        }

        // Create two lists to store unique elements
        List<Integer> uniqueInNums1 = new ArrayList<>();
        List<Integer> uniqueInNums2 = new ArrayList<>();

        // Find elements unique to nums1
        for (int num : set1) {
            if (!set2.contains(num)) {
                uniqueInNums1.add(num);
            }
        }

        // Find elements unique to nums2
        for (int num : set2) {
            if (!set1.contains(num)) {
                uniqueInNums2.add(num);
            }
        }

        // Return the result as a list of lists
        List<List<Integer>> result = new ArrayList<>();
        result.add(uniqueInNums1);
        result.add(uniqueInNums2);

        return result;
    }
}
