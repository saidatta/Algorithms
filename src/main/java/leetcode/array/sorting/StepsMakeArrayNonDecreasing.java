package leetcode.array.sorting;

import java.util.Stack;

// https://leetcode.com/problems/steps-to-make-array-non-decreasing/description/
public class StepsMakeArrayNonDecreasing {
    // Function to compute the number of steps to make the array non-decreasing
    public int totalSteps(int[] nums) {
        // Stack to keep track of elements and their corresponding step count
        Stack<Pair> stack = new Stack<>();

        // Initializing the stack with the first element and 0 steps
        stack.push(new Pair(nums[0], 0));

        // Variable to store the maximum number of steps required
        int steps = 0;

        // Iterate over the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            int temp = 0;

            // If current element is greater than top of the stack,
            // it means the subarray is decreasing
            while (!stack.isEmpty() && stack.peek().value <= nums[i]) {
                // Track the maximum number of steps seen so far
                temp = Math.max(temp, stack.pop().step);
            }

            // If there's a larger element in the stack, increment the temporary step count
            // Otherwise, reset the temporary step count to 0
            if (!stack.isEmpty()) {
                temp += 1;
            } else {
                temp = 0;
            }

            // Update the overall steps with the maximum step value seen so far
            steps = Math.max(steps, temp);

            // Push the current element and its step count to the stack
            stack.push(new Pair(nums[i], temp));
        }

        // Return the computed steps
        return steps;
    }

    // Helper class to store an element and its corresponding step count
    static class Pair {
        int value;
        int step;

        Pair(int value, int step) {
            this.value = value;
            this.step = step;
        }
    }

    public static void main(String[] args) {
        StepsMakeArrayNonDecreasing sol = new StepsMakeArrayNonDecreasing();
        System.out.println(sol.totalSteps(new int[]{5,3,4,4,7,3,6,11,8,5,11}));  // Expected: 3
        System.out.println(sol.totalSteps(new int[]{4,5,7,7,13}));  // Expected: 0
    }
}

//    we use a stack to keep track of the elements in the array and their corresponding number of steps required to
//    make that portion of the array non-decreasing. Each stack element contains two pieces of information: the value
//    of the element (value) and the number of steps required so far (step).

//        We initialize the stack by pushing the first element with 0 steps.
//        We then iterate over the array. For each element, we compare it with the top of the stack:
//        If the current element is greater than the element at the top of the stack, it means the current element is decreasing in relation to the previous elements. So, we pop elements from the stack until we find an element that's greater than the current element, keeping track of the maximum number of steps seen.
//        If we find a larger element in the stack, we increment the temporary step count (temp). Otherwise, we reset it to 0.
//        We then push the current element and its step count to the stack.
//        We continuously update the overall steps count with the maximum step value seen so far.