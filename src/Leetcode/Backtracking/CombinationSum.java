package Leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/combination-sum/#/description
 *
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 * For example, given candidate set [2, 3, 6, 7] and target 7.
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), candidates,0, target, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> temp, int[] candidates, int currSum, int target, int start) {
        if(currSum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if(currSum > target) {
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            currSum += candidates[i];
            helper(result, temp, candidates, currSum, target, i);
            currSum -= candidates[i];
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String [] args) {
        int[] arr = {2,3,6,7};
        CombinationSum combinationSum = new CombinationSum();
        out.println(combinationSum.combinationSum(arr,7));

    }
}
