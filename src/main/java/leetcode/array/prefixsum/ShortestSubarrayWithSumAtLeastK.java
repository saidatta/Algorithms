package leetcode.array.prefixsum;

import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/description/
// https://www.youtube.com/watch?v=K0NgGYEAkA
public class ShortestSubarrayWithSumAtLeastK {

    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int minLength = Integer.MAX_VALUE;
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < prefixSum.length; i++) {
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.getLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{1}, 1)); // Output: 1
        System.out.println(shortestSubarray(new int[]{1, 2}, 4)); // Output: -1
        System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3)); // Output: 3
    }
}

