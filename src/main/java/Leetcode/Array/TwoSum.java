package Leetcode.Array;

import java.util.HashMap;

/**
 * 1 - https://leetcode.com/problems/two-sum/#/description
 *
 * Created by venkatamunnangi on 12/21/16.
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target-nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }

        return result;
    }

    public static void main(String [] args) {
        int [] nums = {2,7,11,15};
        TwoSum twoSum = new TwoSum();

        int [] ans = twoSum.twoSum(nums, 9);
        System.out.println(ans[0]+" "+ ans[1]);
    }
}
