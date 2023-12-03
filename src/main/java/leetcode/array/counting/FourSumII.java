package leetcode.array.counting;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        // Calculate all possible sums of pairs in nums1 and nums2
        for (int a : nums1) {
            for (int b : nums2) {
                sumMap.put(a + b, sumMap.getOrDefault(a + b, 0) + 1);
            }
        }

        int count = 0;
        // For each pair sum in nums3 and nums4, check if the negation is in the map
        for (int c : nums3) {
            for (int d : nums4) {
                count += sumMap.getOrDefault(-(c + d), 0);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FourSumII solution = new FourSumII();
        System.out.println(solution.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2})); // Output: 2
        System.out.println(solution.fourSumCount(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0})); // Output: 1
    }
}
