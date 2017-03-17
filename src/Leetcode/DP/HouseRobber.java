package Leetcode.DP;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * Created by venkatamunnangi on 3/1/17.
 */
public class HouseRobber {
    public  int rob(int [] nums) {
        if (nums == null) {
            return 0;
        }

        int evenIndex = 0, oddIndex = 0;
        for(int i = 0; i<nums.length;i++) {
            if( i % 2 == 0) {
                evenIndex = Math.max(oddIndex , evenIndex + nums[i]);
            } else {
                oddIndex = Math.max(evenIndex , oddIndex + nums[i]);
            }
        }

        return Math.max(evenIndex, oddIndex);
    }



}
