package leetcode.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

// https://www.youtube.com/watch?v=aX1F2-DrBkQ
// https://leetcode.com/problems/sum-of-subarray-minimums
public class MinSubarraySums {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2, 4};
        int[] arr2 = {11, 81, 94, 43, 3};
        System.out.println(sumSubarrayMins(arr1)); // Output: 17
        System.out.println(sumSubarrayMins(arr2)); // Output: 444
    }

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] prevLess = new int[n];
        int[] nextLess = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            prevLess[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nextLess[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prevLess[i];
            long right = nextLess[i] - i;
            sum = (sum + arr[i] * left * right) % MOD;
        }

        return (int) sum;
    }
}
