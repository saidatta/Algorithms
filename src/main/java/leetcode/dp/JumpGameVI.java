package leetcode.dp;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/jump-game-vi/description/
public class JumpGameVI {
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = nums[n - 1];
        Deque<Integer> deque = new LinkedList<>();
        deque.add(n - 1);

        for (int i = n - 2; i >= 0; i--) {
//            To compute the values of dp efficiently, we can use a sliding window with the help of a deque
//            to keep track of the maximum values of the next k positions.
            while (!deque.isEmpty() && deque.peekFirst() > i + k) {
                // reduce the sliding window to be <= k.
                deque.pollFirst();
            }

            // dp[i] is the sum of nums[i] and the maximum value of dp[j] for the next k positions.
            dp[i] = nums[i] + dp[deque.peekFirst()];

            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        return dp[0];
    }

    // Test
    public static void main(String[] args) {
        int[] nums1 = {1,-1,-2,4,-7,3};
        System.out.println(maxResult(nums1, 2));  // 7

        int[] nums2 = {10,-5,-2,4,0,3};
        System.out.println(maxResult(nums2, 3));  // 17

        int[] nums3 = {1,-5,-20,4,-1,3,-6,-3};
        System.out.println(maxResult(nums3, 2));  // 0
    }

}
