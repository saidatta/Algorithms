package leetcode.dp;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// https://leetcode.com/problems/jump-game-viii/
public class JumpGameVIII {
    public long minCost(int[] nums, int[] costs) {
        int[] greater = nextGreaterOrEquals(nums);
        int[] smaller = nextSmaller(nums);
        long[] minCost = new long[nums.length];
        Arrays.fill(minCost, Long.MAX_VALUE);
        minCost[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            if (greater[i] != -1) {
                minCost[greater[i]] = Math.min(minCost[greater[i]], minCost[i] + costs[greater[i]]);
            }
            if (smaller[i] != -1) {
                minCost[smaller[i]] = Math.min(minCost[smaller[i]], minCost[i] + costs[smaller[i]]);
            }
        }

        return minCost[nums.length - 1];
    }

    private int[] nextGreaterOrEquals(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] < nums[stack.peekFirst()]) {
                stack.addFirst(i);
            } else {
                while (!stack.isEmpty() && nums[i] >= nums[stack.peekFirst()]) {
                    res[stack.removeFirst()] = i;
                }
                stack.addFirst(i);
            }
        }

        return res;
    }

    private int[] nextSmaller(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[i] >= nums[stack.peekFirst()]) {
                stack.addFirst(i);
            } else {
                while (!stack.isEmpty() && nums[i] < nums[stack.peekFirst()]) {
                    res[stack.removeFirst()] = i;
                }
                stack.addFirst(i);
            }
        }

        return res;
    }
}
