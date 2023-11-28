package leetcode.backtracking.math;

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
        List<List<Integer>> combinations = new ArrayList<>();
        findCombinationsRecursive(combinations, new ArrayList<>(), candidates, target, 0, 0);
        return combinations;
    }

    private void findCombinationsRecursive(List<List<Integer>> combinations,
                                           List<Integer> currentCombination,
                                           int[] candidates,
                                           int target,
                                           int currentSum,
                                           int currentIndex) {
        // If the current combination equals the target, add to result list
        if(currentSum == target) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        // If the current sum exceeds the target, terminate this branch
        if(currentSum > target) {
            return;
        }

        // Try including each candidate in the current combination
        for(int i = currentIndex; i < candidates.length; i++) {
            currentCombination.add(candidates[i]);
            currentSum += candidates[i];

            // Continue search from current index to allow repeated numbers
            findCombinationsRecursive(combinations, currentCombination, candidates, target, currentSum, i);

            // Undo the last choice (backtrack)
            currentSum -= candidates[i];
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void main(String [] args) {
        int[] arr = {2,3,6,7};
        CombinationSum combinationSum = new CombinationSum();
        out.println(combinationSum.combinationSum(arr,7));

    }
}
