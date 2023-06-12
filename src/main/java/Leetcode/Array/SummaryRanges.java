package Leetcode.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/summary-ranges/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class SummaryRanges {
    private final List<String> ranges = new ArrayList<>();
    private int start;
    private int prev;

    public List<String> summaryRanges(int... nums) {
        if (nums == null || nums.length == 0) {
            return ranges;
        }

        start = nums[0];
        prev = start;

        if(nums.length == 1) {
            ranges.add(Integer.toString(start));
        }

        for (int i = 1; i < nums.length; i++) {
            if (isNotConsecutive(nums[i])) {
                addRange();
                if (isLastElement(nums, i)) {
                    addLastElement(nums[i]);
                }
                start = nums[i];
            } else if (isLastElement(nums, i)) {
                addLastRange(nums[i]);
            }
            prev = nums[i];
        }
        return ranges;
    }

    private void addRange() {
        if (start == prev) {
            ranges.add(Integer.toString(start));
        } else {
            ranges.add(start + "->" + prev);
        }
    }

    private void addLastElement(int num) {
        ranges.add(Integer.toString(num));
    }

    private void addLastRange(int num) {
        ranges.add(start + "->" + num);
    }

    private boolean isNotConsecutive(int num) {
        return num != prev + 1;
    }

    private boolean isLastElement(int[] nums, int i) {
        return i == nums.length - 1;
    }

    public static void main(String [] args) {
        SummaryRanges summaryRanges = new SummaryRanges();

        System.out.println(summaryRanges.summaryRanges(0,1,2,4,5,7));
    }
}
