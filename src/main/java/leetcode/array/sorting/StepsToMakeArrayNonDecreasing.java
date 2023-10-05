package leetcode.array.sorting;

import java.util.*;

// https://leetcode.com/problems/steps-to-make-array-non-decreasing/description/
public class StepsToMakeArrayNonDecreasing {
    public int makeArrayIncreasing(int[] nums) {
        int steps = 0;

        while (true) {
            List<Integer> toRemove = new ArrayList<>();
            boolean needRemoval = false;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    toRemove.add(i);
                    needRemoval = true;
                }
            }

            if (!needRemoval) {
                break;
            }

            // Use a list to hold the new array after removal
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (!toRemove.contains(i)) {
                    newList.add(nums[i]);
                }
            }

            // Convert the list back to an array
            nums = newList.stream().mapToInt(i -> i).toArray();

            steps++;
        }

        return steps;
    }

    public static void main(String[] args) {
        StepsToMakeArrayNonDecreasing solution = new StepsToMakeArrayNonDecreasing();

        int[] nums1 = {5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11};
        System.out.println(solution.makeArrayIncreasing(nums1));  // Expected: 3

        int[] nums2 = {4, 5, 7, 7, 13};
        System.out.println(solution.makeArrayIncreasing(nums2));  // Expected: 0
    }
}

