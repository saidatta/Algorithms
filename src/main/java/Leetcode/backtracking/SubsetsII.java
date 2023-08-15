package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/subsets-ii/
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class SubsetsII {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> al = new ArrayList<>();
            Arrays.sort(nums);
            subsetsBacktrack(al, new ArrayList<>(), nums, 0);
            return al;
        }

        // nlogn + 2^N
        private void subsetsBacktrack(List<List<Integer>> al, List<Integer> temp, int[] nums, int start) {
            int n = nums.length;
            al.add(new ArrayList<>(temp));
            for(int i = start; i< n; i++) {
                if(i > start && nums[i] == nums[i-1]) {
                    // make sure its the second one && check if previous and second one is equal.
                    continue;
                }

                temp.add(nums[i]);
                subsetsBacktrack(al, temp, nums, i+1);
                temp.remove(temp.size()-1);
            }
        }
}
