package leetcode.design.datastructure;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/finding-pairs-with-a-certain-sum/description/
public class FindSumPairs {
    private final int[] nums1;
    private final int[] nums2;
    private final Map<Integer, Integer> freqMap;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freqMap = new HashMap<>();

        // Building the frequency map for nums2
        for (int num : nums2) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        // Update frequency map: Decrease old value count and increase new value count
        freqMap.put(nums2[index], freqMap.get(nums2[index]) - 1);
        nums2[index] += val;
        freqMap.put(nums2[index], freqMap.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int count = 0;
        for (int num : nums1) {
            count += freqMap.getOrDefault(tot - num, 0);
        }
        return count;
    }
}
