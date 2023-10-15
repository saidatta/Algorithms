package leetcode.stack.slidingwindow;

import java.util.Stack;

public class LargestHistogramArea {
//
//    The basic idea is to maintain a stack of indices. This stack keeps the indices of buildings with increasing
//    height. Before adding a new building to the stack, pop the buildings that are taller than the new one.
//    The building popped out represents the height of a rectangle with the new building as the right boundary and
//    the current stack top as the left boundary. Calculate its area and update the answer with the maximum area found.
//
//    Here's the algorithm:
//
//    Initialize a stack s.
//    For each bar:
//    While the stack is not empty and heights[current_index] is less than heights[s.top()]:
//    Pop the stack.
//    Compute the area:
//    height = height of the popped bar.
//            width = current index - stack top after popping - 1.
//    Update the max area if needed.
//    Push the current index onto the stack.
//    After the bars are finished, there might be some indices remaining in the stack. For each remaining index,
//    the width of the rectangle is the difference between the total number of bars and the index stored right below
//    the current index in the stack.
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            // only pop when you encounter a building that is smaller than the one in stack.
            // keep track of window of increasing buildings, and once you encounter the smaller one.
            // keep calculating area accordingly by popping all buildings in the stack. track maxArea
            // Then, calculate with the new building.
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestHistogramArea solution = new LargestHistogramArea();

        int[] heights10= {1,2,3,4,3};
        System.out.println(solution.largestRectangleArea(heights10)); // Expected output: 10
        int[] heights0= {6, 7, 5, 2, 4, 5, 9, 3};
        System.out.println(solution.largestRectangleArea(heights0)); // Expected output: 10

        // Test case 1
        int[] heights1 = {2, 1, 5, 6, 2, 3};
        System.out.println(solution.largestRectangleArea(heights1)); // Expected output: 10

        // Test case 2
        int[] heights2 = {2, 4};
        System.out.println(solution.largestRectangleArea(heights2)); // Expected output: 4
    }
}
