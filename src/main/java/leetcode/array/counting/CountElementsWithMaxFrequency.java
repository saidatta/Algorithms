package leetcode.array.counting;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/count-elements-with-maximum-frequency/description/
public class CountElementsWithMaxFrequency {
    public static int countElementsWithMaxFrequency(int[] nums) {
        // Step 1: Count frequencies of each element
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Find the maximum frequency
        int maxFrequency = 0;
        for (int freq : frequencyMap.values()) {
            maxFrequency = Math.max(maxFrequency, freq);
        }

        // Step 3: Count elements with maximum frequency
        int count = 0;
        for (int freq : frequencyMap.values()) {
            if (freq == maxFrequency) {
                count += freq;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] numsExample1 = {1, 2, 2, 3, 1, 4};
        System.out.println(countElementsWithMaxFrequency(numsExample1)); // Output: 4

        int[] numsExample2 = {1, 2, 3, 4, 5};
        System.out.println(countElementsWithMaxFrequency(numsExample2)); // Output: 5
    }
}

