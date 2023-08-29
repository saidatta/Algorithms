package leetcode.array.counting;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-complete-subarrays-in-an-array/
 */
public class CountCompleteSubarrays {
    public int countCompleteSubarrays(int[] nums) {
        int distinctCount = (int) Arrays.stream(nums).distinct().count();
        int[] frequency = new int[2001]; // the maximum value of nums[i] is 2000 according to the problem constraints
        int left = 0, right = 0, count = 0, result = 0;

        while (right < nums.length) {
            if (frequency[nums[right]] == 0) {
                count++;
            }
            frequency[nums[right]]++;

            while (count == distinctCount) {
                result += nums.length - right;
                frequency[nums[left]]--;
                if (frequency[nums[left]] == 0) {
                    count--;
                }
                left++;
            }
            right++;
        }

        return result;

    }
}
