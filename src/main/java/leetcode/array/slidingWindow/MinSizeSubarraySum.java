package leetcode.array.slidingWindow;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/description/
 */
public class MinSizeSubarraySum {
    //    Input: target = 7, nums = [2,3,1,2,4,3]
//    Output: 2
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum >= target) {
                while (sum >= target) {
                    sum -= nums[j++];
                }
                min = Math.min(min, i - j + 2);
            }
        }
        return min < Integer.MAX_VALUE ? min : 0;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum solution = new MinSizeSubarraySum();

        int target = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};

        int minSubArrayLength = solution.minSubArrayLen(target, nums);
        System.out.println("The minimum length of a subarray with sum at least " + target + " is " + minSubArrayLength);
    }

}
