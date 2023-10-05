package leetcode.array.counting;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/unique-number-of-occurrences/
public class UniqueNumberOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        // Store the frequency of elements in the unordered map.
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Store the frequency count of elements in the unordered set.
        Set<Integer> freqSet = new HashSet<>(freq.values());

        // If the set size is equal to the map size,
        // It implies frequency counts are unique.
        return freq.size() == freqSet.size();
    }

    public static void main(String[] args) {
        UniqueNumberOccurrences sol = new UniqueNumberOccurrences();

        System.out.println(sol.uniqueOccurrences(new int[]{1,2,2,1,1,3}));  // Expected: true
        System.out.println(sol.uniqueOccurrences(new int[]{1,2}));  // Expected: false
        System.out.println(sol.uniqueOccurrences(new int[]{-3,0,1,-3,1,1,1,-3,10,0}));  // Expected: true
    }
}
