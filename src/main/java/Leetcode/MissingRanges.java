package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/missing-ranges/#/description
 * <p>
 * Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its
 * missing ranges.
 * <p>
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * <p>
 * Created by venkatamunnangi on 5/9/17.
 */
public class MissingRanges {
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        List<String> ranges = new ArrayList<>();

        // Check for null and empty array inputs
        if (nums == null || nums.length == 0) {
            addRange(ranges, lower, upper);
            return ranges;
        }

        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            int missingLowerRange = (i == 0) ? lower : nums[i - 1] + 1;
            int missingUpperRange = (i == n) ? upper : nums[i] - 1;

            addRange(ranges, missingLowerRange, missingUpperRange);
        }

        return ranges;
    }

    private void addRange(List<String> ranges, int lo, int hi) {
        if (lo > hi) {
            // Don't add anything if lower range is higher than upper range
            return;
        }

        StringBuilder range = new StringBuilder();
        range.append(lo);
        if (lo != hi) {
            range.append("->").append(hi);
        }
        ranges.add(range.toString());
    }


    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int start = lower;
        List<List<Integer>> bucket = new ArrayList<>();

        for (int num : nums) {
            if (start < num) {
                bucket.add(Arrays.asList(start, num - 1));
            }
            start = num + 1;
        }

        if (start <= upper) {
            bucket.add(Arrays.asList(start, upper));
        }

        return bucket;
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
