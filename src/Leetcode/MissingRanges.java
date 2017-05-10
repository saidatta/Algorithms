package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/#/description
 *
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 *
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 *
 * Created by venkatamunnangi on 5/9/17.
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        ArrayList<String> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            if(lower == upper) {
                ans.add(""+lower);
            } else {
                ans.add("" + lower + "->" + upper);
            }
            return ans;
        }

        int size = nums.length;

        if(nums[0] - lower >= 1) {
            ans.add(makeString(lower,nums[0]-1));
        }

        if(nums[0] == upper) {
            return ans;
        }


        for(int i = 1;i< size ; i++) {
            int relativeLower = nums[i-1];
            int relativeUpper = nums[i];
            if (nums[i] - nums[i - 1] > 1) {
                ans.add(makeString(relativeLower + 1, relativeUpper - 1));
            }
        }

        if(upper - nums[size-1] > 0) {
            ans.add(makeString(nums[size-1]+1, upper));
        }

        return ans;
    }

    private String makeString(int x, int y) {
        if(x == y) {
            return ""+x;
        }
        return ""+x+"->"+y;
    }

    public static void main(String... args) {
        MissingRanges missingRanges = new MissingRanges();
        int[] nums = {};
        System.out.println(missingRanges.findMissingRanges(nums, 1, 1));
    }
}
