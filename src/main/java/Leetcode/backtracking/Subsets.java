package Leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/subsets/#/description
 *
 * If nums = [1,2,3], a solution is:
 * [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class Subsets {

    //2^N
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String [] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1,2,3};
        out.println(subsets.subsets(nums));
    }
}
