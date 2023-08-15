package Leetcode.Array;

/**
 * https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations/
 */
public class LargestElementArrayMergeOps {
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long ans  = nums[n-1] , sum = nums[n-1];
        for(int i= nums.length-2 ; i>=0 ; i--){
            if(nums[i] <= sum) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }

            ans = Math.max(ans,sum);
        }
        return ans;
    }
}
