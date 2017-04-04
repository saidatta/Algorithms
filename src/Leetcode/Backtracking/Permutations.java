package Leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/#/description
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        backtrack(permutations, new ArrayList<>(), nums);
        return permutations;
    }

    public void backtrack(List<List<Integer>> al, List<Integer> temp, int[] nums) {
        if(temp.size() == nums.length) {
            al.add(new ArrayList<>(temp));
        } else {
            for(int i = 0; i<nums.length; i++) {
                if(!temp.contains(nums[i])) {
                    temp.add(nums[i]);
                    backtrack(al, temp, nums);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
}
