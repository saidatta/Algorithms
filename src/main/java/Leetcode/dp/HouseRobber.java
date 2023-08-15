package Leetcode.dp;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * Created by venkatamunnangi on 3/1/17.
 */
public class HouseRobber {
    public  int rob(int... nums) {
        if (nums == null) {
            return 0;
        }

        int previousEvenMaxIndex = 0, previousMaxOddIndex = 0;
        for(int i = 0; i<nums.length;i++) {
            if( i % 2 == 0) {
                previousEvenMaxIndex = Math.max(previousMaxOddIndex , previousEvenMaxIndex + nums[i]);
            } else {
                previousMaxOddIndex = Math.max(previousEvenMaxIndex , previousMaxOddIndex + nums[i]);
            }
        }

        return Math.max(previousEvenMaxIndex, previousMaxOddIndex);
    }

    public static void main(String [] args) {

        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(5,1,1,5));

    }


}
