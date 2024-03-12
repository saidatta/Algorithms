package leetcode.array.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class UniqueSumElements {
    public static int sumOfUnique(int[] nums) {
        // Create a map to count occurrences of each number
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Sum up numbers that have count 1
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                result += entry.getKey();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2};
        System.out.println(sumOfUnique(nums));  // Expected output: 4
    }
}
