package Leetcode;

/**
 * https://leetcode.com/problems/move-zeroes/#/description
 *
 * Created by venkatamunnangi on 3/23/17.
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int p1 = 0, p2 = 1;
        // 0 1 0 3 12 ->1 0 0 3 12
        //-> 1 3 12 0 0
        while(p2 < nums.length) {
            if(nums[p1] == 0 && nums[p2] != 0) {
                nums[p1] = nums[p2];
                nums[p2] = 0;
                p1++;
                p2++;
            } else {
                if(nums[p1] != 0) {
                    p1++;
                }
                p2++;
            }
        }
    }

}
