package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/find-the-longest-equal-subarray/description/
public class LongestEqualSubarray {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int n = nums.size();

        int start = 0, maxLength = 0;
        for (int end = 0; end < n; end++) {
            int currentNum = nums.get(end);
            frequencyMap.put(currentNum, frequencyMap.getOrDefault(currentNum, 0) + 1);
            // max occurrence of the equal number
            maxLength = Math.max(maxLength, frequencyMap.get(currentNum));

            // different numbers in the current window
            int currentDiff = end - start + 1 - maxLength;
            if (currentDiff > k) {
                int startNum = nums.get(start);
                frequencyMap.put(startNum, frequencyMap.get(startNum) - 1);
                start++;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestEqualSubarray solution = new LongestEqualSubarray();
        List<Integer> nums = Arrays.asList(1, 3, 2, 3, 1, 3);
        int k = 2;
        System.out.println(solution.longestEqualSubarray(nums, k)); // Sample output
    }
}
