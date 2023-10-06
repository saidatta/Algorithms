package leetcode.array.counting;

import java.util.*;

// https://leetcode.com/problems/majority-element-ii/description
// This problem can be solved using the Boyer-Moore Voting Algorithm. Given the problem constraint where we're looking
// for all elements that appear more than n/3 times, at most only 2 elements can appear more than n/3 times in the array
//
//        The intuition is similar to the original Boyer-Moore algorithm, where we kept track of one majority element.
//        Here, we keep track of two potential majority elements.
//
//        Here's how the algorithm works:
//
//        We first pass through the array to find two potential majority elements.
//        We then pass through the array again to verify that these elements are indeed majorities.
public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                count1++;
            } else if (candidate2 != null && candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1++;
            } else if (count2 == 0) {
                candidate2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            }
            if (candidate2 != null && candidate2 == num) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }
        return result;
    }

    public static void main(String[] args) {
        MajorityElementII solver = new MajorityElementII();

        int[] nums1 = {3, 2, 3};
        System.out.println(solver.majorityElement(nums1));  // [3]

        int[] nums2 = {1};
        System.out.println(solver.majorityElement(nums2));  // [1]

        int[] nums3 = {1, 2};
        System.out.println(solver.majorityElement(nums3));  // [1, 2]
    }
}

