package leetcode.math;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/next-greater-element-ii/description/
public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                // find the next greater number for the current number.
                result[stack.pop()] = num;
            }
            if (i < n) {
                // only push to stack the numbers pre-circular iteration.
                stack.push(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElementII solution = new NextGreaterElementII();
        int[] nums = {1, 2, 1};
        int[] result = solution.nextGreaterElements(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
        // Output: 2 -1 2
    }
}
