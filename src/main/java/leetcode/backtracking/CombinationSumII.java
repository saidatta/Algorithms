package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/#/description
 * <p>
 * Created by venkatamunnangi on 4/4/17.
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, result, new ArrayList<>(), target, 0);
        return result;
    }

    private void backtrack(int[] candidates, List<List<Integer>> result, List<Integer> temp, int remain, int start) {
        if (remain < 0) {
            return;
        }

        if (remain == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                // to allow first element and skip duplicates.
                continue;
            }
            temp.add(candidates[i]);
            backtrack(candidates, result, temp, remain - candidates[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {10, 1, 2, 7, 6, 1, 5};
        CombinationSumII combinationSum = new CombinationSumII();
        System.out.println(combinationSum.combinationSum2(arr, 8));

    }
}
