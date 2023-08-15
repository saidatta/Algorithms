package Leetcode.greedy;

// https://leetcode.com/problems/jump-game/
public class JumpGameI {

    /**
     *  maxReach keeps track of the furthest position that can be reached from any of the previous positions. If at
     *  any point, i (the current position) exceeds maxReach, it implies that the end cannot be reached from the current
     *  position. Otherwise, if the loop completes without returning false, it means the last index can be reached.
     *
     * Example 1:
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Example 2:
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
     * impossible to reach the last index.
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int... nums) {
        int n = nums.length;
        int maxReach = 0;
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    public static void main(String [] args) {
        System.out.println(canJump(2,3,1,2,4));
        System.out.println(canJump(3,2,1,0,4));
    }
}
