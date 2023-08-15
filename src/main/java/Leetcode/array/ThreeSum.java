package Leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/#/description
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * Created by venkatamunnangi on 9/25/16.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int... nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                // no duplicates
                int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        // found triplet.
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            // forward lo pointer if found duplicate.
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            // forward hi pointer if found duplicate.
                            hi--;
                        }

                        // go to next number.
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        // since array sorted. increment the lowest number.
                        lo++;
                    } else {
                        // since array sorted. decrement the highest number.
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int p1 = i + 1;
            int p2 = nums.length - 1;
            while (p1 < p2) {
                int sum = nums[i] + nums[p1] + nums[p2];
                if (sum == 0) {
                    ArrayList<Integer> sp = new ArrayList<>();
                    sp.add(nums[i]);
                    sp.add(nums[p1]);
                    sp.add(nums[p2]);

                    ans.add(sp);
                    p1++;
                } else if (sum < 0) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
