package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/#/description
 * https://www.youtube.com/watch?v=rSA3t6BDDwg
 *
 * Related
 * https://leetcode.com/problems/combination-sum-ii/solutions/16878/combination-sum-i-ii-and-iii-java-solution-see-the-similarities-yourself/
 *
 * Created by venkatamunnangi on 4/4/17.
 */
public class CombinationSumII {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // Sort the array to handle duplicates easily
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // If remaining sum becomes negative, no solution can exist, so return
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            // If remaining sum becomes 0, add the current combination
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    // Skip duplicates
                    continue;
                }
                tempList.add(candidates[i]);
                // Use next element onwards
                backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
                // Backtrack
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println(combinationSum2(candidates1, target1));  // Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println(combinationSum2(candidates2, target2));  // Output: [[1, 2, 2], [5]]
    }
}
