package leetcode.array.counting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

// https://leetcode.com/problems/random-pick-index/description/
public class RandomPickIndex {
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indices = map.get(target);
        int randomIndex = ThreadLocalRandom.current().nextInt(indices.size());
        return indices.get(randomIndex);
    }

}
