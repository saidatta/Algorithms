package leetcode.array.counting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// https://leetcode.com/problems/random-pick-index/description/
public class RandomPickIndex {
    private final Map<Integer, List<Integer>> map = new HashMap<>();

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

    // reservoir Sampling for very large arrays. - https://leetcode.com/problems/random-pick-index/editorial/
    class Solution {

        private final int[] nums;
        private final Random rand;

        public Solution(int[] nums) {
            this.nums = nums;
            this.rand = new Random();
        }

        public int pick(int target) {
            int n = this.nums.length;
            int count = 0;
            int idx = 0;
            for (int i = 0; i < n; ++i) {
                // if nums[i] is equal to target, i is a potential candidate
                // which needs to be chosen uniformly at random
                if (this.nums[i] == target) {
                    // increment the count of total candidates
                    // available to be chosen uniformly at random
                    count++;
                    // we pick the current number with probability 1 / count (reservoir sampling)
                    if (rand.nextInt(count) == 0) {
                        idx = i;
                    }
                }
            }
            return idx;
        }
    }

    public class Solution2 {
        int[] nums;

        public Solution2(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    if (ThreadLocalRandom.current().nextInt(++count) == 0) {
                        result = i;
                    }
                }
            }
            return result;
        }
    }

}
