package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/#/description
 * <p>
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
 * <p>
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * <p>
 * Created by venkatamunnangi on 5/9/17.
 */
public class MissingRanges {
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<String>();
        if (nums == null) {
            return list;
        }
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            int missingLowerRange = 0, missingEndRange=0;

            if (i == 0) {
                missingLowerRange = lower;
            } else {
                if (nums[i - 1] == upper) {
                    // if upper value is equal to array value. we dont need to create Range string.
                    break;
                } else {
                    missingLowerRange = nums[i - 1] + 1;
                }
            }

            if (i == n) {
                missingEndRange = upper;
            } else {
                if (nums[i] == lower) {
                    // if lower value is equal to array value. we dont need to create Range string.
                    continue;
                } else {
                    missingEndRange = nums[i] - 1;
                }
            }

            addRange(list, missingLowerRange, missingEndRange);
        }

        return list;
    }

    private void addRange(List<String> list, int lo, int hi) {
        if(lo <= hi) {
            if (lo == hi) {
                list.add(String.valueOf(lo));
            }
            else {
                list.add(lo + "->" + hi);
            }
        }
    }



    //l == u  : l
    // l != u && len == 0 : l -> u
    // (curr - prev) > 1 : prev+1 -> curr -1
    // (curr == end && upper - end > 1) curr+1 -> end
    // curr - lower > 1 -> lower -> curr-1

    public static void main(String... args) {
        MissingRanges missingRanges = new MissingRanges();
        int[] nums = {};
        System.out.println(missingRanges.findMissingRanges2(nums, 1, 1));
    }
}
