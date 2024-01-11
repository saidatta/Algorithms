package leetcode.array.grid;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/convert-an-array-into-a-2d-array-with-conditions/
// https://www.youtube.com/watch?v=9pl1QiaGgmI
class Convert2DConditions {
    public List<List<Integer>> findMatrix(int[] nums) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxFreq = 0;

        // Calculate the frequency of each number and find the maximum frequency
        for (int num : nums) {
            int freq = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, freq);
            maxFreq = Math.max(maxFreq, freq);
        }

        // Create the 2D array with minimal rows
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < maxFreq; i++) {
            resultList.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            // Distribute the number across the rows
            for (int i = 0; i < freq; i++) {
                resultList.get(i).add(num);
            }
        }

        return resultList;
    }
}
