package Leetcode.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/summary-ranges/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class SummaryRanges {

    public List<String> summaryRanges(int... nums) {
        List<String> result = new ArrayList<String>();
        if (nums == null || nums.length == 0)
            return result;
        if (nums.length == 1) {
            result.add(Integer.toString(nums[0]));
        }

        int prev = nums[0]; // previous element
        int first = prev; // first element of each range
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != prev+1) {
                if(first == prev) {
                    result.add(Integer.toString(first));
                } else {
                    result.add(first +"->"+prev);
                }

                if(i == nums.length-1) {
                    result.add(Integer.toString(nums[i]));
                }

                first = nums[i];

            } else {
                if(i == nums.length-1) {
                    result.add(first+"->"+nums[i]);
                }
            }
            prev = nums[i];
        }
        return result;
    }

    public static void main(String [] args) {
        SummaryRanges summaryRanges = new SummaryRanges();

        System.out.println(summaryRanges.summaryRanges(0,1,2,4,5,7));
    }
}
