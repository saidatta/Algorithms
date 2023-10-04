package leetcode.array.sorting;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://leetcode.com/problems/find-target-indices-after-sorting-array/
public class FindTargetIndicesAfterSorting {
    public static List<Integer> findTargetIndices(int[] nums, int target) {
        int n = nums.length;

        // Sort the nums array
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 2, 3};
        int target = 2;
        System.out.println(findTargetIndices(nums, target)); // Expected: [1, 2]
    }
}

